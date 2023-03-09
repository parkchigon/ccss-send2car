package com.lgu.ccss.send2car.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.send2car.mapper.DatabaseMapper;
import com.lgu.ccss.send2car.model.Send2CarParam;
import com.lgu.ccss.send2car.service.worker.Send2CarWorkerThread;

@Service
public class Send2CarServiceImpl implements Send2CarService {

	private final Logger logger = LoggerFactory.getLogger(Send2CarServiceImpl.class);

	@Autowired
	private DatabaseMapper mapper;
	
	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;

	@Value("#{config['send2car.worker.threadCnt']}")
	private String threadCnt;
	
	@Value("#{config['send2car.notiTime.min']}")
	private String notiTimeMin;
	
	@Value("#{config['android.send2car.appPush.title']}")
	private String androidNotiMsgTitle;
	
	@Value("#{config['ios.send2car.appPush.title']}")
	private String iosNotiMsgTitle;
	
	@Value("#{config['android.send2car.appPush.scheduleTitle']}")
	private String androidNotiMsgScheduleTitle;
	
	@Value("#{config['ios.send2car.appPush.scheduleTitle']}")
	private String iosNotiMsgScheduleTitle;
	
	private static Send2CarWorkerThread[] workerThreadArr = null;

	public void doTask() {
		
		String notiTimeSec = String.valueOf(Integer.parseInt(notiTimeMin) * 60);
		
		try {
			if (workerThreadArr == null) {
				makeWorkerThread();
			}

			int notCompleteQueueCount = this.getThreadQueueCount();
			if (notCompleteQueueCount > 0) {
				logger.info("Before job not complete! queueCount:" + notCompleteQueueCount);
				Thread.sleep(500);
				return;
			}
			
			Send2CarParam send2CarParam = new Send2CarParam();
			send2CarParam.setSvrId(serverId);
			send2CarParam.setNotiTimeSec(notiTimeSec);
		
			doService(mapper.selectSend2CarList(send2CarParam));
			doService(mapper.selectSend2CarListByArrivHopeTime(send2CarParam));

		} catch (Exception e) {
			logger.error("Exception !! :", e);
		}
	}

	public void makeWorkerThread() {
		workerThreadArr = new Send2CarWorkerThread[Integer.parseInt(threadCnt)];
		for (int i = 0; i < Integer.parseInt(threadCnt); i++) {
			Send2CarWorkerThread workerThread = new Send2CarWorkerThread(i + 1);
			workerThread.setWorkerNum(i);
			workerThread.setServerId(serverId);
			workerThread.setAndroidNotiMsgScheduleTitle(androidNotiMsgScheduleTitle);
			workerThread.setAndroidNotiMsgTitle(androidNotiMsgTitle);
			workerThread.setIosNotiMsgScheduleTitle(iosNotiMsgScheduleTitle);
			workerThread.setIosNotiMsgTitle(iosNotiMsgTitle);
			workerThreadArr[i] = workerThread;
			logger.info("Make WorkerThread " + i);
		}
	}

	public int getThreadQueueCount() {
		int queueCount = 0;

		for (int i = 0; i < Integer.parseInt(threadCnt); i++) {
			queueCount = queueCount + workerThreadArr[i].getQueueCount();
		}

		return queueCount;
	}
	
	private void doService(List<Send2CarVO> searchList) {
		if (searchList == null) {
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("\n##################################################\n"
					+ "SEND2CAR_LIST({})\n"
					+ "##################################################", searchList);
		}

		for (int i = 0; i < searchList.size(); i++) {
			workerThreadArr[i % Integer.parseInt(threadCnt)].addQueue(searchList.get(i));
		}
	}
}
