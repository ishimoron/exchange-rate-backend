package com.example.exchangerateportal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.exchangerateportal.entities.Currency;
import com.example.exchangerateportal.repositories.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

	@Mock
	private CurrencyRepository currencyRepository;

	@InjectMocks
	private CurrencyService currencyService;

	@BeforeEach
	public void setUp() {
		// No need for explicit initialization with MockitoExtension
	}

	@Test
	public void testSaveCurrencyData() throws IOException {
		// Mock the behavior of currencyRepository.findByDateAndCurrency()
		when(currencyRepository.findByDateAndCurrency(anyString(), anyString())).thenReturn(null);

		// Define test data
		String jsonData = "[{\"date\":\"2024-05-17\",\"currency\":\"USD\",\"rate\":1.5}]";

		// Call the method under test
		currencyService.saveCurrencyData(jsonData);

		// Verify that currencyRepository.save() was called once
		verify(currencyRepository, times(1)).save(any(Currency.class));
	}

	@Test
	public void testFindCurrencyHistory() throws JsonProcessingException {
		// Mock the behavior of currencyRepository.findHistoryByCurrency()
		List<Currency> currencyHistory = new ArrayList<>();
		currencyHistory.add(new Currency());
		when(currencyRepository.findHistoryByCurrency(anyString())).thenReturn(currencyHistory);

		// Call the method under test
		String historyJson = currencyService.findCurrencyHistory("USD");

		// Verify that the returned JSON string matches the expected value
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJson = objectMapper.writeValueAsString(currencyHistory);
		assertEquals(expectedJson, historyJson);
	}
}
