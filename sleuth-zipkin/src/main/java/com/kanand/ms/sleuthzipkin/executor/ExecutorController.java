package com.kanand.ms.sleuthzipkin.executor;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorController {
	//let's wire this executor into our controller and use it in a new request mapping method:

	Logger logger = LoggerFactory.getLogger(ExecutorController.class);
	@Autowired
	private Executor executor;
	@Autowired
	AsyncService asyncService;
	    
	    @GetMapping("/new-thread")
	    public String helloSleuthNewThread() {
	        logger.info("New Thread");
	        Runnable runnable = () -> {
	            try {
	                Thread.sleep(100L);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            logger.info("I'm inside the new thread - with a new span");
	        };
	        executor.execute(runnable);

	        logger.info("I'm done - with the original span");
	        return "success";

	   }
	    
	    @GetMapping("/async")
	    public String helloSleuthAsync() {
	        logger.info("Before Async Method Call");
	        try {
				asyncMethod();// this wont create a new thread consider as normal methos bcoz its in the same class/To make Async we need to put into anoter class
				asyncService.asyncMethod();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        logger.info("After Async Method Call");
	        
	        return "success";
	    }
	    
	    
	    @Async
	    public void asyncMethod() throws InterruptedException {
	        logger.info("Start Async Method");
	        Thread.sleep(1000L);
	        logger.info("End Async Method");
	    }
	    
	    
}
