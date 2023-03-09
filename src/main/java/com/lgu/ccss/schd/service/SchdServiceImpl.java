package com.lgu.ccss.schd.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.model.ScheduleSetVO;
import com.lgu.ccss.schd.service.worker.SchdWorkerThread;
import com.lgu.ccss.send2car.mapper.DatabaseMapper;
import com.lgu.common.map.MapAgent;
import com.lgu.common.util.DateUtils;

@Service
public class SchdServiceImpl implements SchdService {
	private final Logger logger = LoggerFactory.getLogger(SchdServiceImpl.class);

	@Autowired
	private DatabaseMapper mapper;
	
	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;

	@Value("#{config['scheduler.worker.threadCnt']}")
	private String threadCnt;

	@Value("#{config['scheduler.db.select.count']}")
	private String schdRowNum;

	@Value("#{config['scheduler.db.select.prevhour']}")
	private String prevHour;
	
	@Value("#{config['scheduler.db.update.prevhour']}")
	private String updatePrevHour;
	
	@Value("#{config['send2car.notiTime.min']}")
	private String notiTimeMin;

	@Autowired
	MapAgent mapAgent;
	
	private static SchdWorkerThread[] workerThreadArr = null;

	public void doTask() {

		try {

			if (workerThreadArr == null) {
				makeWorkerThread();
			}

			int notCompleteQueueCount = this.getThreadQueueCount();
			if (notCompleteQueueCount > 0) {
				logger.info("Before job not complete! queueCount:" + notCompleteQueueCount);
				return;
			}

			// prevHour에 설정된 이전 시간만큼 미설정된 Send2Car설정정보를 가져온다.
			ScheduleSetVO searchVO = new ScheduleSetVO();
			Date pastDt = DateUtils.getCurrentDate(Calendar.HOUR_OF_DAY, -1 * Integer.parseInt(prevHour));
			String sendDt = DateUtils.getFormattedTime(pastDt, "yyyy-MM-dd") + " 00:00:00";
			searchVO.setSendDt(sendDt);
			searchVO.setRownum(schdRowNum);
			searchVO.setSvrId(serverId);

			if (logger.isDebugEnabled()) {
				logger.debug("SCHEDULE_QUERY_ARG({})", searchVO);
			}
			
			// 2019-11-25, JYJ
			// 전제 : 기설정된 일정스케줄은 당일 00시 이후에 바로 금일 전송할 Send2Car정보를 모두 저장한다.  
			// 검색조건 :
			//   (기존)sendDT부터 +2일(당일 00시)전부터 +1일까지 검색되므로 격일로 Send2Car가 저장되는 오류 
			//   (신규)prevHour시간 전부터 금일 00시까지의 Send2Car 반복일정에 설정된 정보를 모두 가져와서 저장한다.
			List<ScheduleSetVO> searchList = mapper.selectScheduleList(searchVO);
			
			if (searchList == null) {
				return;
			}

			if (logger.isDebugEnabled()) {
				logger.debug("\n##################################################\n"
						+ "SCHEDULE_LIST({})\n"
						+ "##################################################", searchList);
			}

			for (int i = 0; i < searchList.size(); i++) {
				workerThreadArr[i % Integer.parseInt(threadCnt)].addQueue(searchList.get(i));
			}

		} catch (Exception e) {
			logger.error("Exception !! :", e);
		}
	}

	public void makeWorkerThread() {
		workerThreadArr = new SchdWorkerThread[Integer.parseInt(threadCnt)];
		for (int i = 0; i < Integer.parseInt(threadCnt); i++) {
			SchdWorkerThread workerThread = new SchdWorkerThread(i + 1);
			workerThread.setWorkerNum(i);
			workerThread.setServerId(serverId);
			workerThread.setNotiTimeMin(notiTimeMin);
			workerThread.setUpdatePrevHour(updatePrevHour);
			workerThread.setMapAgent(mapAgent);
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
}
