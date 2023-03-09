package com.lgu.ccss.schd.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lgu.ccss.schd.service.SchdService;

@Service
public class ReserveScheduler {
	private final Logger logger = LoggerFactory.getLogger(ReserveScheduler.class);

	@Autowired
	private SchdService schdService;
	
	@Scheduled(fixedDelay=15000, initialDelay=10000)
	public void startWork() {

		try {
			logger.info("###### START Reserve Scheduler DAEMON #####");
			
			// 일정스케줄에 의한 SendToCar 테이블에 입력하기 위한 프로세스
			schdService.doTask();

		} catch (Exception e) {
			logger.error("{}", e);

		} finally {
			logger.info("###### END Reserve Scheduler DAEMON #####");
		}

	}
	
	
}
