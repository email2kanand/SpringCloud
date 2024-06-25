package com.kanand.ms.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kanand.ms.currencyexchangeservice.CurrencyExchangeRepository;

@RestController
public class ExchnageController {
	@Autowired
	private CurrencyExchangeRepository repository;
	@Autowired
	private Environment environment;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchange(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
	
	private CurrencyExchange getExchange(String from, String to) {
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

		if (currencyExchange == null) {
			throw new RuntimeException("Unable to Find data for " + from + " to " + to);
		}

		String port = environment.getProperty("local.server.port");

		currencyExchange.setEnvironment(port);

		return currencyExchange;
	}

}
