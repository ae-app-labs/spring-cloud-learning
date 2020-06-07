package com.example.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Environment env;
	
	@Autowired
	private ExchangeValueRepository repo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = repo.findByFromAndTo(from, to);
		
		exchangeValue.setPort( Integer.parseInt(env.getProperty("local.server.port")) );
		
		logger.info("{}", exchangeValue);
		return exchangeValue;
	}
}