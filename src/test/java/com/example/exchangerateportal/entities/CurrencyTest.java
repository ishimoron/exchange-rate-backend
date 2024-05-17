package com.example.exchangerateportal.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CurrencyTest {

	@Test
	public void testCurrencyEntity() {
		// Create a Currency object
		Currency currency = new Currency();
		currency.setDate("2024-05-17");
		currency.setRate(1.5);
		currency.setCurrency("USD");

		// Test getters
		assertEquals("2024-05-17", currency.getDate());
		assertEquals(1.5, currency.getRate());
		assertEquals("USD", currency.getCurrency());

		// Test setters
		currency.setDate("2024-05-18");
		currency.setRate(1.6);
		currency.setCurrency("EUR");

		assertEquals("2024-05-18", currency.getDate());
		assertEquals(1.6, currency.getRate());
		assertEquals("EUR", currency.getCurrency());
	}
}
