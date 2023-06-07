package com.kanand.ms.currencyconversionservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kanand.ms.currencyconversionservice.controller.CurrencyConversion;


//@FeignClient(name="currency-exchange", url="localhost:8000")
/*
 * if using naming server it will take it from naming server and also do client side load balancing
 * (now latest version uses netflix eureka earlier it was ribbon) if there are 
 * multi instances registred with 'CURRENCY-EXCHANGE-SERVICE' same name what is registred with naming server
 */
@FeignClient(name="CURRENCY-EXCHANGE-SERVICE")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable("from") String from,
			@PathVariable("to") String to);

}