package com.lgu.ccss.send2car.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lgu.ccss.send2car.service.Send2CarService;

@Service
public class Send2CarScheduler {
	private final Logger logger = LoggerFactory.getLogger(Send2CarScheduler.class);

	@Autowired
	private Send2CarService send2CarService;
	
	@Scheduled(fixedDelay=1000, initialDelay=10000)
	public void startWork() {

		try {
			//logger.info("###### START SEND2CAR DAEMON #####");
				
			send2CarService.doTask();

		} catch (Exception e) {
			logger.error("{}", e);

		} finally {
			//logger.info("###### END SEND2CAR DAEMON #####");
		}

		// logger.info("$$$$$$$" +
		// CommMessageUtil.getMessage("result_success"));
	}
}
