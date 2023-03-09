package com.lgu.ccss.send2car.service.worker;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lgu.ccss.App;
import com.lgu.ccss.common.model.AppPushQueueVO;
import com.lgu.ccss.common.model.CarPushQueueVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.MgrAppUserVO;
import com.lgu.ccss.common.model.MqttContentJSON;
import com.lgu.ccss.common.model.Send2CarJSON;
import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.common.model.setInfo.JsonSetInfo;
import com.lgu.ccss.config.PropertyConfig;
import com.lgu.ccss.send2car.mapper.DatabaseMapper;
import com.lgu.common.tlo.TloWriter;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.JsonUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;


public class Send2CarWorkerThread {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final int WORK_QUEUE_SIZE = 1000;

	RealWorker worker = null;

	int threadID;
	int workerNum;
	String DEBUG_STRING = null;

	private Vector<Send2CarVO> msgVector = null;

	private DatabaseMapper mapper;

	private PropertyConfig propertyConfig;
	
	private String androidNotiMsgTitle;
	private String iosNotiMsgTitle;
	private String androidNotiMsgScheduleTitle;
	private String iosNotiMsgScheduleTitle;
	private String serverId;

	public Send2CarWorkerThread() {
		
	}

	public Send2CarWorkerThread(int tid) {
		threadID = tid;
		DEBUG_STRING = "[WorkerThread-" + threadID + "] : ";
		worker = new RealWorker();
		worker.start();

		msgVector = new Vector<Send2CarVO>(WORK_QUEUE_SIZE);

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
	
	public void setAndroidNotiMsgTitle(String androidNotiMsgTitle) {
		this.androidNotiMsgTitle = androidNotiMsgTitle;
	}
	
	public void setAndroidNotiMsgScheduleTitle(String androidNotiMsgScheduleTitle) {
		this.androidNotiMsgScheduleTitle = androidNotiMsgScheduleTitle;
	}
	
	public void setIosNotiMsgTitle(String iosNotiMsgTitle) {
		this.iosNotiMsgTitle = iosNotiMsgTitle;
	}
	
	public void setIosNotiMsgScheduleTitle(String iosNotiMsgScheduleTitle) {
		this.iosNotiMsgScheduleTitle = iosNotiMsgScheduleTitle;
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

					doSend2CarService((Send2CarVO) object);

					// sleep(100);

				} catch (Exception e) {
					logger.error("Exception ", e);
				}
			}
			logger.error("Thread Exit!! ");
		}
	}

	public void addQueue(Send2CarVO send2CarVO) {
		synchronized (msgVector) {
			msgVector.add(send2CarVO);
		}
	}

	public int getQueueCount() {
		return msgVector.size();
	}

	private void doSend2CarService(Send2CarVO send2CarVO) throws Exception {
		String orgSendStatus = send2CarVO.getSendStatus();
		
		// send to car push
		mapper.insertTbCarPushQueue(makeCarPushMessage(send2CarVO));

		// update send2car status
		send2CarVO.setSendStatus(Send2CarVO.SEND_STATUS_SEND);
		int updateCnt = mapper.updateSend2Car(send2CarVO);

		if (logger.isDebugEnabled()) {
			logger.debug("udpate sendStatus. send2CarId({}) membId({}) cnt({})", send2CarVO.getSendToCarId(),
					send2CarVO.getMembId(), updateCnt);
		}
		
		String arrivHopeTime = send2CarVO.getArrivHopeTime();
		if (arrivHopeTime != null && arrivHopeTime.length() > 0) {
			// send to app push
			AppPushQueueVO appPushMsg = makeAppPushMessage(send2CarVO, orgSendStatus);
			if(appPushMsg!=null)
				mapper.insertTbAppPushQueue(appPushMsg);
		}
	}

	private CarPushQueueVO makeCarPushMessage(Send2CarVO send2carVO) throws Exception {
		// get memb
		MembVO membVO = new MembVO();
		membVO.setMembId(send2carVO.getMembId());
		membVO = mapper.selectMemberbyID(membVO);

		// make car push message
		CarPushQueueVO carPushQueueVO = new CarPushQueueVO();
		carPushQueueVO.setMsgId(KeyGenerator.createCommonShardKey());
		carPushQueueVO.setMsgStatus(CarPushQueueVO.MSG_STATUS_READY);
		carPushQueueVO.setCode(Send2CarVO.CODE_TARGET_PUSH_CODE);
		carPushQueueVO.setMsgTitle("");

		
		String code = null;
		if (send2carVO.getArrivHopeTime() != null) {
			code = Send2CarVO.CODE_RESERVE_TARGET_PUSH;
		} else {
			code = Send2CarVO.CODE_TARGET_PUSH_CODE;
		}
		
		Send2CarJSON send2car = new Send2CarJSON();
		send2car.setId(send2carVO.getSendToCarId());
		send2car.setAddress(AES256Util.AESEncode(membVO.getTransToken(), send2carVO.getTargetAddress()));
		send2car.setLonx(send2carVO.getTargetLonx());
		send2car.setLaty(send2carVO.getTargetLaty());
		
		MqttContentJSON content = new MqttContentJSON();
		content.setCode(code);
		content.setSend2car(send2car);
		carPushQueueVO.setMsgCont(JsonUtil.marshallingJson(content));

		carPushQueueVO.setMsgType(CarPushQueueVO.MASSAGE_TYPE_SINGLE);
		carPushQueueVO.setRecvPhoneNo(membVO.getMembCtn());
		carPushQueueVO.setSendType(CarPushQueueVO.SEND_TYPE_NORMAL);
		carPushQueueVO.setSendDt(DateUtils.getCurrentTime("yyyyMMddHHmmss"));
		
		carPushQueueVO.setRcvReport(CarPushQueueVO.DEF_TRUE);
		carPushQueueVO.setReadReport(CarPushQueueVO.DEF_TRUE);
		carPushQueueVO.setDeviceType(membVO.getMarketType());

		if (logger.isDebugEnabled()) {
			logger.debug("CAR_PUSH({})", carPushQueueVO);
		}

		return carPushQueueVO;
	}

	private AppPushQueueVO makeAppPushMessage(Send2CarVO send2carVO, String orgSendStatus) throws Exception {
		// get connDevice
/*		ConnDeviceVO connDeviceVO = new ConnDeviceVO();
		connDeviceVO.setMembId(send2carVO.getMembId());
		connDeviceVO.setUseYn(ConnDeviceVO.USE_Y);
		connDeviceVO = mapper.getDeviceInfo(connDeviceVO);*/
		
		MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
		mgrAppUserVO.setMgrappId(send2carVO.getMgrappId());
		mgrAppUserVO = mapper.selectMgrUserInfo(mgrAppUserVO);
		
		
		String jsonStr= mgrAppUserVO.getJsonSetInfo();
		if(jsonStr!=null && jsonStr.length()>0) {
			JsonSetInfo jsonSetInfo = 	JsonUtil.unmarshallingJson(jsonStr, JsonSetInfo.class);
			if(jsonSetInfo!=null && jsonSetInfo.getNotifications()!=null) {
				if (Send2CarVO.SEND_STATUS_SCHEDULE.equals(orgSendStatus)) {
					String appPushSet =jsonSetInfo.getNotifications().getNotiSchedDepart();
					if(appPushSet!=null && appPushSet.equals("OFF")) {
						logger.info("SchedDepart Push Off: Not Send App Push Message MGRAPP_INFO({})", mgrAppUserVO);
						return null;
					}
				} else {
					String appPushSet =jsonSetInfo.getNotifications().getNotiDestDepart();
					if(appPushSet!=null && appPushSet.equals("OFF")) {
						logger.info("DestDepart Push Off: Not Send App Push Message MGRAPP_INFO({})", mgrAppUserVO);
						return null;
					}
				}
			}
		}
		
		
		MgrAppUserVO carOemVO = new MgrAppUserVO();
		carOemVO.setMgrappId(send2carVO.getMgrappId());
		carOemVO = mapper.selectCarOemInfo(carOemVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("MGRAPP_INFO({}) CAR_OEM({})", mgrAppUserVO, carOemVO);
		}

		// make car push message
		AppPushQueueVO appPushQueueVO = new AppPushQueueVO();
		appPushQueueVO.setMsgId(KeyGenerator.createCommonShardKey());
		appPushQueueVO.setMsgStatus(CarPushQueueVO.MSG_STATUS_READY);
		
		String title = "";
		String code = "";
		if (Send2CarVO.SEND_STATUS_SCHEDULE.equals(orgSendStatus)) {
			code = AppPushQueueVO.CODE_SCHEDULE_PUSH;
			if (MgrAppUserVO.OS_TYPE_ANDROID.equals(mgrAppUserVO.getOsType())) {
				title = androidNotiMsgScheduleTitle;
			} else {
				title = iosNotiMsgScheduleTitle;
			}
		} else {
			code = AppPushQueueVO.CODE_TARGET_PUSH;
			if (MgrAppUserVO.OS_TYPE_ANDROID.equals(mgrAppUserVO.getOsType())) {
				title = androidNotiMsgTitle;
			} else {
				title = iosNotiMsgTitle;
			}
		}
		appPushQueueVO.setCode(code);
		appPushQueueVO.setMsgTitle(title);
		
		for (byte e : title.getBytes("utf-8")) {
			System.out.print(String.format("%02x", e));
		}
		System.out.println("\n");
		
		
		for (byte e : send2carVO.getTargetNm().getBytes("utf-8")) {
			System.out.print(String.format("%02x", e));
		}
		System.out.println("\n");
		
		
		if (MgrAppUserVO.OS_TYPE_ANDROID.equals(mgrAppUserVO.getOsType())) {			
			appPushQueueVO.setMsgCont(AppPushPayloadCreator.getAndroidPayload(code, title, send2carVO));
			appPushQueueVO.setDeviceType("A");
			
		} else {
			appPushQueueVO.setMsgCont(AppPushPayloadCreator.getIPhonePayload(code, title, send2carVO));
			appPushQueueVO.setDeviceType("I");
		}

		appPushQueueVO.setMsgType(CarPushQueueVO.MASSAGE_TYPE_SINGLE);
		if(mgrAppUserVO.getPushId() != null && Send2CarVO.USE_Y.equals(mgrAppUserVO.getPushKeyRegYn())){
			
			appPushQueueVO.setRecvPhoneNo(mgrAppUserVO.getPushId());
		}
		appPushQueueVO.setSendType(AppPushQueueVO.SEND_TYPE_NORMAL);
		appPushQueueVO.setSvrId(serverId);
		appPushQueueVO.setSendDt(DateUtils.getCurrentTime("yyyyMMddHHmmss"));
		appPushQueueVO.setReqPart(AppPushQueueVO.REQ_PART_SP);
		appPushQueueVO.setCarOem(carOemVO.getOemNm());

		if (logger.isDebugEnabled()) {
			logger.debug("APP_PUSH({})", appPushQueueVO);
		}

		return appPushQueueVO;
	}

/*	private String makeAppPushMessageContent(Send2CarVO send2carVO, String title) {
		// target name
		String targetName = send2carVO.getTargetNm();

		// arrive time
		Date arriveDate = DateUtils.stringToDate(send2carVO.getArrivHopeTime(), "yyyy-MM-dd HH:mm:ss");
		String arriveTime = DateUtils.getFormattedTime(arriveDate, "HH:mm a", new Locale("en", "US"));

		// est time
		int totalSec = Integer.parseInt(send2carVO.getEstTime());
		int day = totalSec / (60 * 60 * 24);
		int hour = (totalSec - day * 60 * 60 * 24) / (60 * 60);
		int minute = (totalSec - day * 60 * 60 * 24 - hour * 3600) / 60;

		String estTime = null;
		if (hour > 0) {
			estTime = String.valueOf(hour) + "시간 " + String.valueOf(minute) + "분 소요";
		} else {
			estTime = String.valueOf(minute) + "분 소요";
		}

		// start time
		Calendar cal = Calendar.getInstance();
		cal.setTime(arriveDate);
		cal.add(Calendar.SECOND, totalSec);
		String startTime = DateUtils.getFormattedTime(cal.getTime(), "HH:mm a", new Locale("en", "US"));

		String notiContent = new String(notiMsgContent);
		//String notiContent = new String("목적지 : {target}\n" +
		//"목표 도착 시간 : {arriveTime}\n" + "\n" + "[{startTime}] 출발\n" + "({estTime})\n");
		 
		notiContent = notiContent.replace("{target}", targetName);
		notiContent = notiContent.replace("{arriveTime}", arriveTime);
		notiContent = notiContent.replace("{startTime}", startTime);
		notiContent = notiContent.replace("{estTime}", estTime);

		return title + "\n" + notiContent;
	}*/

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Send2CarVO send2carVO = new Send2CarVO();
		send2carVO.setTargetNm("동탄신도시 롯데캐슬");
		send2carVO.setArrivHopeTime("2018-02-14 22:15:00");
		send2carVO.setEstTime("3500");

		System.out.println(AppPushPayloadCreator.getAndroidPayload("S002", "일정 알림", send2carVO));
		System.out.println(AppPushPayloadCreator.getIPhonePayload("S002", "일정 알림", send2carVO));
	}
}

class AppPushPayloadCreator {
	public static final String PAYLOAD_CODE = "code";
	public static final String PAYLOAD_TITLE = "title";
	public static final String PAYLOAD_CONTENT = "content";
	
	public static final String CONTENT_TARGET = "target";
	public static final String CONTENT_TARGET_ARRIVE_TIME = "targetArriveTime";
	public static final String CONTENT_START_TIME = "startTime";
	public static final String CONTENT_ELAPSE_TIME = "elapseTime";
	
	public static String getAndroidPayload(String code, String title, Send2CarVO send2carVO) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> payloadMap = getPayload(code, title, send2carVO);
		
		return JsonUtil.marshallingJson(payloadMap);
	}
	
	public static String getIPhonePayload(String code, String title, Send2CarVO send2carVO) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> alertMap = new LinkedHashMap<String, Object>();
		alertMap.put("alert", getPayload(code, title, send2carVO));
		alertMap.put("sound", "ring");
		
		Map<String, Object> payloadMap = new LinkedHashMap<String, Object>();
		payloadMap.put("aps", alertMap);
		
		return JsonUtil.marshallingJson(payloadMap);
	}
	
	private static Map<String, Object> getPayload(String code, String title, Send2CarVO send2carVO) {
		// target name
		String targetName = send2carVO.getTargetNm();

		// arrive time
		Date arriveDate = DateUtils.stringToDate(send2carVO.getArrivHopeTime(), "yyyy-MM-dd HH:mm:ss");
		String arriveTime = DateUtils.getFormattedTime(arriveDate, "yyyyMMddHHmmss");

		// est time
		String estTime = send2carVO.getEstTime();		

		// start time
		Calendar cal = Calendar.getInstance();
		cal.setTime(arriveDate);
		cal.add(Calendar.SECOND, -(Integer.parseInt(estTime)));
		String startTime = DateUtils.getFormattedTime(cal.getTime(), "yyyyMMddHHmmss");
		
		// set result map
		Map<String, String> contentMap = new LinkedHashMap<String, String>();
		contentMap.put(CONTENT_TARGET, targetName);
		contentMap.put(CONTENT_TARGET_ARRIVE_TIME, arriveTime);
		contentMap.put(CONTENT_START_TIME, startTime);
		contentMap.put(CONTENT_ELAPSE_TIME, estTime);
		
		Map<String, Object> payloadMap = new LinkedHashMap<String, Object>();
		payloadMap.put(PAYLOAD_CODE, code);
		payloadMap.put(PAYLOAD_TITLE, title);
		payloadMap.put(PAYLOAD_CONTENT, contentMap);

		return payloadMap;
	}
}