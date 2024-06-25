package com.kanand.ms.sleuthzipkin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;


@RestController
public class SleuthZipkinController {
	
	Logger logger = LoggerFactory.getLogger(SleuthZipkinController.class);
	@GetMapping("/")
    public String helloSleuth() {
        logger.info("Hello Sleuth");
        return "success";
    }
	@GetMapping("/same-span")
    public String helloSleuthSameSpan() throws InterruptedException {
        logger.info("Same Span");
        doSomeWorkSameSpan();
        return "success";
}
	 public void doSomeWorkSameSpan() throws InterruptedException {
	        Thread.sleep(1000L);
	        logger.info("thread name"+Thread.currentThread().getName());
	        logger.info("Doing some work");
	    }
	 
	 @GetMapping("/create-new-span")
	 public String helloSleuthNewSpan() throws InterruptedException {
	     logger.info("New Span");
	     doSomeWorkNewSpan();
	     return "success";
	 }
	 @Autowired
	 private Tracer tracer;
	 public void doSomeWorkNewSpan() throws InterruptedException {
	     logger.info("I'm in the original span");

	     Span newSpan = tracer.nextSpan().name("newSpan").start();
	     try (SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
	         Thread.sleep(1000L);
	         logger.info("I'm in the new span doing some cool work that needs its own span");
	     } finally {
	         newSpan.finish();
	     }

	     logger.info("I'm in the original span");
	 }
}
