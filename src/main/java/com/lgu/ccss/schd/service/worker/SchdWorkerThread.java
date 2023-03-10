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
		 * 1. ?????? ?????? ?????? 00??? ????????? ?????? Send2Car??? ???????????????
		 * 2. ????????? Send2Car???  Push ????????????????????? ????????? ???????????? Send2Car????????? ??? ??? ??????
		 * 3. ????????? ????????? ?????? ????????? Send2Car??? ?????? Set?????? ?????? ????????????????????? ?????????
		 */
		
		// NONE_SCH : ????????? ?????? ?????? ???????????????????????? ?????? ??????
		// TODAY_SCH : ???????????? ???????????????????????? ??????
		// TOMORROW_SCH : ???????????? ???????????????????????? ??????
		// ALL_SCH : ????????? ?????? ?????? ???????????????????????? ??????
		SchEnum schSet = SchEnum.NONE_SCH;
		
		// ?????? ????????? Set
		Date nowDt = new Date();
		if (isValidDay(nowDt, scheduleVO.getRepeatAlarmDay()) == true) {
			schSet = SchEnum.TODAY_SCH;	
		}
		// ??????(??????) ????????? Set
		Date tomorrowDt = new Date(nowDt.getTime() + (1000 * 60 * 60 * 24));
		if (isValidDay(tomorrowDt, scheduleVO.getRepeatAlarmDay()) == true) {
			if (schSet == SchEnum.TODAY_SCH) {
				// ??????/?????? ????????? Set??? ????????????
				schSet = SchEnum.ALL_SCH;
			} else {
				schSet = SchEnum.TOMORROW_SCH;
			}
		}

		logger.debug("##### ????????????????????? ??????  Enum :" + schSet);
				
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
		
		
		// ?????????????????? Push???????????? ???????????? ??????
		String arrivHopeDtStr = DateUtils.getFormattedTime(arrivHopeDt, "yyyyMMdd");
		String limitDtStr = DateUtils.getFormattedTime(limitDt, "yyyyMMdd");
		
		/*
		 * arrivHopeTime ??????
		 */
		Date now = new Date();
		// ???????????????????????? ????????? ???, ?????? ???????????? ????????? ?????????????????? Send2Car???????
		if (now.after(limitDt)) {
			
			// ????????????????????? ??????????????? ?????????????????? (????????????????????? ????????? ?????????)
			if (!arrivHopeDtStr.equals(limitDtStr)) {
				
				// ?????? ????????????????????? ?????? ?????????????????? ???????????? ??????????
				if (schSet==SchEnum.TOMORROW_SCH || schSet==SchEnum.ALL_SCH) {
					
					// ????????? (????????? ??????)
					arrivHopeDt = DateUtils.getAddDate(arrivHopeDt, Calendar.HOUR, 24);
					targetDtStr = DateUtils.getFormattedTime(arrivHopeDt, "yyyyMMdd");
					
					arrivHopeTime = String.format("%d-%02d-%02d %s:00", Integer.parseInt(targetDtStr.substring(0, 4)),
							Integer.parseInt(targetDtStr.substring(4, 6)), Integer.parseInt(targetDtStr.substring(6, 8)),
							scheduleVO.getArrivHopeTime());
				// ?????? ??????????????? ???????????? ?????? ?????? skip
				} else {
					
					logger.debug("UNDELIVERABLE TIME[1]. NOW({}) LIMIT({}) SCHEDULE({})", now, limitDt, scheduleVO);
					return null;
				}
			// ????????????????????? ??????????????? ????????? ????????? skip
			} else {
				
				logger.debug("UNDELIVERABLE TIME[2]. NOW({}) LIMIT({}) SCHEDULE({})", now, limitDt, scheduleVO);
				return null;
			}
		// ????????? ??????????????? Send2Car???????
		} else {
			if (schSet == SchEnum.TODAY_SCH || schSet == SchEnum.ALL_SCH) {
					
				arrivHopeTime = String.format("%d-%02d-%02d %s:00", Integer.parseInt(targetDtStr.substring(0, 4)),
						Integer.parseInt(targetDtStr.substring(4, 6)), Integer.parseInt(targetDtStr.substring(6, 8)),
						scheduleVO.getArrivHopeTime());
			} else {
					
				logger.debug("DAY OF THE WEEK NOT SCHEDULED. NOW({}) LIMIT({}) SCHEDULE({})", now, limitDt, scheduleVO);
				// ?????? ???????????? ????????? ??????
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
