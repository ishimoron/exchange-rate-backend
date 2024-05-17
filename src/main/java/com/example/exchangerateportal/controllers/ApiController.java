package com.example.exchangerateportal.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangerateportal.services.CurrencyService;
import com.example.exchangerateportal.services.FxRatesService;

@RestController
@CrossOrigin
public class ApiController {

	@Autowired
	private FxRatesService fxRatesService;

	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/currencies")
	public String getCurrency() {
		return fxRatesService.getFxRates();
	}

	@GetMapping("/api/currencies")
	@Scheduled(cron = "0 0 15 * * ?")
	public String processAndSaveCurrencyData() throws IOException {
		String fxRatesResponse = fxRatesService.getFxRates();

		currencyService.saveCurrencyData(fxRatesResponse);
		return fxRatesResponse;
	}

	@GetMapping("/api/history/{currency}")
	public String findCurrencyGet(@PathVariable String currency) {
		return currencyService.findCurrencyHistory(currency);
	}

	@PostMapping("/api/history/{currency}")
	public String findCurrencyPost(@PathVariable String currency) {
		return currencyService.findCurrencyHistory(currency);
	}

}
