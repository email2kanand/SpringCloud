package com.kanand.ms.sleuthzipkin.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
Logger logger = LoggerFactory.getLogger(this.getClass());
	
	  @Async
	    public void asyncMethod() throws InterruptedException {
	        logger.info("Start Async Method");
	        Thread.sleep(1000L);
	        logger.info("End Async Method");
	    }
}
