package com.kanand.ms.sleuthzipkin.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

//@Configuration
@EnableAsync
@EnableScheduling
public class ScheduleConfig extends AsyncConfigurerSupport implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.setScheduler(schedulingExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor schedulingExecutor() {
		return Executors.newScheduledThreadPool(1);
	}

}