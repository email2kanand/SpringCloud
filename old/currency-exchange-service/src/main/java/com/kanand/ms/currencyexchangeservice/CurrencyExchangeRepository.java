package com.kanand.ms.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kanand.ms.currencyexchangeservice.controller.CurrencyExchange;

public interface CurrencyExchangeRepository 
	extends JpaRepository<CurrencyExchange, Long> {
	CurrencyExchange findByFromAndTo(String from, String to);
}