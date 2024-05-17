package com.example.exchangerateportal.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class FxRatesServiceTest {

	@Autowired
	private FxRatesService fxRatesService;

	@MockBean
	private RestTemplate restTemplate;

	@Test
	public void testGetFxRates() {
		String mockResponse = "<FxRates>...</FxRates>";
		when(restTemplate.getForObject("https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU",
				String.class))
				.thenReturn(mockResponse);

		String fxRates = fxRatesService.getFxRates();

		assertNotNull(fxRates);
	}
}
