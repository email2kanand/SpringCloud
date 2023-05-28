package com.kanand.ms.sleuthzipkin.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Scheduled(fixedDelay = 30000)
	public void scheduledWork() throws InterruptedException {
		logger.info("Start some work from the scheduled task");
		asyncMethod();
		logger.info("End work from scheduled task");
	}
	 @Async
	    public void asyncMethod() throws InterruptedException {
	        logger.info("Start Async Method");
	        Thread.sleep(1000L);
	        logger.info("End Async Method");
	    }
	    
}