package com.example.exchangerateportal.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangerateportal.entities.Currency;
import com.example.exchangerateportal.repositories.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	public void saveCurrencyData(String jsonData) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Currency[] currencies = objectMapper.readValue(jsonData, Currency[].class);

		for (Currency currency : currencies) {
			Currency existingCurrency = currencyRepository.findByDateAndCurrency(
					currency.getDate(), currency.getCurrency());

			if (existingCurrency == null) {
				Currency newCurrency = new Currency();
				newCurrency.setDate(currency.getDate());
				newCurrency.setCurrency(currency.getCurrency());
				newCurrency.setRate(currency.getRate());
				currencyRepository.save(newCurrency);
			}
		}
	}

	public String findCurrencyHistory(String currency) {
		try {
			List<Currency> currencyHistory = currencyRepository.findHistoryByCurrency(currency);
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(currencyHistory);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
