package com.lgu.ccss.schd.service.worker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgu.ccss.App;
import com.lgu.ccss.common.model.CarPushQueueVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.MgrAppUserVO;
import com.lgu.ccss.common.model.ScheduleSetVO;
import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.config.PropertyConfig;
import com.lgu.ccss.send2car.mapper.DatabaseMapper;
import com.lgu.common.map.MapAgent;
import com.lgu.common.map.MapConst;
import com.lgu.common.map.model.findStatRoute.ResFindStatRouthSearchJSON;
import com.lgu.common.map.model.findStatRoute.TimeListJSON;
import com.lgu.common.tlo.TloWriter;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.keygenerator.KeyGenerator;

public class SchdWorkerThread {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	RealWorker worker = null;

	int threadID;
	int workerNum;
	String DEBUG_STRING = null;

	private Vector<ScheduleSetVO> msgVector = null;

	private DatabaseMapper mapper;

	private PropertyConfig propertyConfig;

	private String serverId;

	private String notiTimeMin;

	@SuppressWarnings("unused")
	private String updatePrevHour;

	private MapAgent mapAgent;

	enum SchEnum{
	    NONE_SCH, TODAY_SCH, TOMORROW_SCH, ALL_SCH
	}
	
	public SchdWorkerThread() {
	}

	public SchdWorkerThread(int tid) {
		threadID = tid;
		DEBUG_STRING = "[WorkerThread-" + threadID + "] : ";
		worker = new RealWorker();
		worker.start();

		msgVector = new Vector<ScheduleSetVO>(1000);

		mapper = (DatabaseMapper) App.ctx.getBean(DatabaseMapper.class);
		propertyConfig = (PropertyConfig) App.ctx.getBean(PropertyConfig.class);

		TloWriter.tloFilePath = propertyConfig.getTloConfigPath();

	}

	public void setWorkerNum(int workerNum) {
		this.workerNum = workerNum;
	}
	
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public void setNotiTimeMin(String notiTimeMin) {
		this.notiTimeMin = notiTimeMin;
	}

	public void setUpdatePrevHour(String updatePrevHour) {
		this.updatePrevHour = updatePrevHour;
	}

	public void setMapAgent(MapAgent mapAgent) {
		this.mapAgent = mapAgent;
	}

	public synchronized void wake() {
		this.notify();
	}

	private class RealWorker extends Thread {
		public RealWorker() {
			setDaemon(true);
			setName("WorkerThread-" + (threadID + 100));
		}

		private boolean runFlag = true;

		public void run() {
			while (runFlag) {

				try {
					Object object = null;
					synchronized (msgVector) {
						if (msgVector.size() > 0)
							object = msgVector.remove(0);
					}
					if (object == null) {
						sleep(20);
						continue;
					}

					doScheduleService(((ScheduleSetVO) object));

					// sleep(100);
				} catch (Exception e) {
					logger.error("Exception ", e);

				} finally {

				}
			}
			logger.error("Thread Exit!! ");
		}
	}

	public void addQueue(ScheduleSetVO scheduleVO) {
		synchronized (msgVector) {
			msgVector.add(scheduleVO);
		}
	}

	public int getQueueCount() {
		return msgVector.size();
	}

	private void doScheduleService(ScheduleSetVO scheduleVO) throws Exception {
/*		scheduleVO.setSendDt(DateUtils.getAddDate(scheduleVO.getSendDt(), Calendar.HOUR_OF_DAY,
				-1 * Integer.parseInt(updatePrevHour), "yyyy-MM-dd HH:mm:ss"));
		int updateCnt = mapper.updateScheduleTime(scheduleVO);*/
		
		scheduleVO.setSendDt(DateUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		int updateCnt = mapper.updateScheduleTime(scheduleVO);

		if (logger.isDebugEnabled()) {
			logger.debug("udpate sendDt. scheduleId({}) membId({}) cnt({})", scheduleVO.getScheduleId(),
					scheduleVO.getMembId(), updateCnt);
		}

		
		// get memb
		MembVO membVO = new MembVO();
		membVO.setMembId(scheduleVO.getMembId());
		membVO = mapper.selectMemberbyID(membVO);

		Send2CarVO send2CarVO = makeSend2Car(scheduleVO, membVO);
		if (send2CarVO == null) {
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("SEND2CAR({}))", send2CarVO);
		}

		mapper.insertTargetSend(send2CarVO);
	}

	private Send2CarVO makeSend2Car(ScheduleSetVO scheduleVO, MembVO membVO) throws Exception {
		
		/*
		 * Date : 2019/11/25
		 * Author : JYJ
		 * 1. 보통 금일 새벽 00시 이후쯤 당일 Send2Car가 설정되므로
		 * 2. 설정된 Send2Car의  Push 예측전송시간이 어제가 되는경우 Send2Car전송을 할 수 없음
		 * 3. 그래서 이러한 경우 내일로 Send2Car을 미리 Set하여 금일 예측발송하도록 수정함
		 */
		
		// NONE_SCH : 오늘과 내일 모두 요일스케줄설정이 없는 경우
		// TODAY_SCH : 오늘에만 요일스케줄설정된 경우
		// TOMORROW_SCH : 내일에만 요일스케줄설정된 경우
		// ALL_SCH : 오늘과 내일 모두 요일스케줄설정된 경우
		SchEnum schSet = SchEnum.NONE_SCH;
		
		// 당일 스케줄 Set
		Date nowDt = new Date();
		if (isValidDay(nowDt, scheduleVO.getRepeatAlarmDay()) == true) {
			schSet = SchEnum.TODAY_SCH;	
		}
		// 내일(새벽) 스케줄 Set
		Date tomorrowDt = new Date(nowDt.getTime() + (1000 * 60 * 60 * 24));
		if (isValidDay(tomorrowDt, scheduleVO.getRepeatAlarmDay()) == true) {
			if (schSet == SchEnum.TODAY_SCH) {
				// 오늘/내일 스케줄 Set이 되어있음
				schSet = SchEnum.ALL_SCH;
			} else {
				schSet = SchEnum.TOMORROW_SCH;
			}
		}

		logger.debug("##### 요일스케줄설정 정보  Enum :" + schSet);
				
		MgrAppUserVO carOemVO = new MgrAppUserVO();
		carOemVO.setMgrappId(scheduleVO.getMgrappId());
		carOemVO = mapper.selectCarOemInfo(carOemVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("CAR_OEM({})", carOemVO);
		}
		
		String targetDtStr = DateUtils.getFormattedTime(nowDt, "yyyyMMdd");
		String arrivHopeTime = String.format("%d-%02d-%02d %s:00", Integer.parseInt(targetDtStr.substring(0, 4)),
				Integer.parseInt(targetDtStr.substring(4, 6)), Integer.parseInt(targetDtStr.substring(6, 8)),
				scheduleVO.getArrivHopeTime());

		String estSec = getEstTime(scheduleVO, membVO, arrivHopeTime);

		Date arrivHopeDt = DateUtils.stringToDate(arrivHopeTime, "yyyy-MM-dd HH:mm:ss");
		Date limitDt = DateUtils.getAddDate(arrivHopeDt, Calendar.SECOND,
				-1 * (Integer.parseInt(estSec) + Integer.parseInt(notiTimeMin)));
		
		
		logger.debug("##### arrivHopeTime:"+arrivHopeTime+" estSec:"+estSec+" limitDt:"+limitDt);
		
		
		// 출발예정일과 Push전송일의 비교하기 위해
		String arrivHopeDtStr = DateUtils.getFormattedTime(arrivHopeDt, "yyyyMMdd");
		String limitDtStr = DateUtils.getFormattedTime(limitDt, "yyyyMMdd");
		
		/*
		 * arrivHopeTime 설정
		 */
		Date now = new Date();
		// 출발예정시간으로 보았을 때, 현재 시간보다 이전에 보내졌어야할 Send2Car인가?
		if (now.after(limitDt)) {
			
			// 출발예정일자과 현재일자가 틀릴경우에만 (출발예정시간이 전날일 경우임)
			if (!arrivHopeDtStr.equals(limitDtStr)) {
				
				// 내일 반복일정요일로 해당 전송스케줄이 설정되어 있는가?
				if (schSet==SchEnum.TOMORROW_SCH || schSet==SchEnum.ALL_SCH) {
					
					// 재설정 (내일로 설정)
					arrivHopeDt = DateUtils.getAddDate(arrivHopeDt, Calendar.HOUR, 24);
					targetDtStr = DateUtils.getFormattedTime(arrivHopeDt, "yyyyMMdd");
					
					arrivHopeTime = String.format("%d-%02d-%02d %s:00", Integer.parseInt(targetDtStr.substring(0, 4)),
							Integer.parseInt(targetDtStr.substring(4, 6)), Integer.parseInt(targetDtStr.substring(6, 8)),
							scheduleVO.getArrivHopeTime());
				// 오늘 반복요일로 설정되어 있을 경우 skip
				} else {
					
					logger.debug("UNDELIVERABLE TIME[1]. NOW({}) LIMIT({}) SCHEDULE({})", now, limitDt, scheduleVO);
					return null;
				}
			// 출발예정일자와 현재일자가 동일한 경우에 skip
			} else {
				
				logger.debug("UNDELIVERABLE TIME[2]. NOW({}) LIMIT({}) SCHEDULE({})", now, limitDt, scheduleVO);
				return null;
			}
		// 금일에 전송가능한 Send2Car인가?
		} else {
			if (schSet == SchEnum.TODAY_SCH || schSet == SchEnum.ALL_SCH) {
					
				arrivHopeTime = String.format("%d-%02d-%02d %s:00", Integer.parseInt(targetDtStr.substring(0, 4)),
						Integer.parseInt(targetDtStr.substring(4, 6)), Integer.parseInt(targetDtStr.substring(6, 8)),
						scheduleVO.getArrivHopeTime());
			} else {
					
				logger.debug("DAY OF THE WEEK NOT SCHEDULED. NOW({}) LIMIT({}) SCHEDULE({})", now, limitDt, scheduleVO);
				// 내일 반복요일 설정은 제외
				return null;
			}
		}
		
		
		logger.debug("SCHEDULE({})",scheduleVO);
		Send2CarVO send2CarVO = new Send2CarVO();
		send2CarVO.setSendToCarId(KeyGenerator.createCommonShardKey());
		send2CarVO.setMembId(scheduleVO.getMembId());
		send2CarVO.setMgrappId(scheduleVO.getMgrappId());
		send2CarVO.setTargetNm(scheduleVO.getTargetNm());
		send2CarVO.setTargetAddress(scheduleVO.getTargetAddress());
		send2CarVO.setTargetLonx(scheduleVO.getTargetLonx());
		send2CarVO.setTargetLaty(scheduleVO.getTargetLaty());
		send2CarVO.setArrivHopeTime(arrivHopeTime);
		send2CarVO.setEstTime(estSec);
		send2CarVO.setSendStatus(Send2CarVO.SEND_STATUS_SCHEDULE);
		send2CarVO.setServiceType(Send2CarVO.CODE_TARGET_PUSH_CODE);
		send2CarVO.setRsvType(CarPushQueueVO.SEND_TYPE_NORMAL);
		send2CarVO.setSvrId(serverId);
		send2CarVO.setScheduleId(scheduleVO.getScheduleId());
		send2CarVO.setTargetRealLonx(scheduleVO.getTargetRealLonx());
		send2CarVO.setTargetRealLaty(scheduleVO.getTargetRealLaty());
		send2CarVO.setTargetPoiId(scheduleVO.getTargetPoiId());
		send2CarVO.setTargetRoadName(scheduleVO.getTargetRoadName());
		send2CarVO.setTargetRoadJibun(scheduleVO.getTargetRoadJibun());
		send2CarVO.setSearchOption(scheduleVO.getSearchOption());
		send2CarVO.setCarOem(carOemVO.getOemNm());

		return send2CarVO;
	}

	private boolean isValidDay(Date date, String alarmDay) {
		if (alarmDay == null || alarmDay.length() != Calendar.DAY_OF_WEEK) {
			return false;
		}

		int targetDay = DateUtils.getDayOfWeek(date);
		int dayIndex = 0;
		if (targetDay == Calendar.SUNDAY) {
			dayIndex = 6;
		} else {
			dayIndex = targetDay - 2;
		}
		
		char onOff = alarmDay.charAt(dayIndex);
		if (onOff == '1') {
			return true;
		}

		return false;
	}

	private String getEstTime(ScheduleSetVO scheduleVO, MembVO membVO, String arrivHopeTime) throws Exception {

		ResFindStatRouthSearchJSON result = mapAgent.findStatRouthSearch(MapConst.URL_FIND_STAT_ROUTH, MapConst.SVC_ROUTH,
				membVO.getMarketType(), membVO.getMembCtn(), scheduleVO, arrivHopeTime);

		List<TimeListJSON> timeList = result.getTime_list();
		if (timeList.size() == 0) {
			return null;
		}
		
		long estSec = timeList.get(0).getEnd_time() - timeList.get(0).getStart_time();
		return String.valueOf(estSec);
	}

}
