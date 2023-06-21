package com.kanand.ms.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanand.ms.limitservice.config.Configuration;

@RestController
public class LimitController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public String getLimit() {
		return "min :"+configuration.getMin();
	}

}
