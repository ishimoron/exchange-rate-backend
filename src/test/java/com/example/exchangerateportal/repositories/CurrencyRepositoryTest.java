package com.example.exchangerateportal.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.exchangerateportal.entities.Currency;

@DataJpaTest
public class CurrencyRepositoryTest {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Test
	public void testFindByDateAndCurrency() {
		// Save a sample Currency object
		Currency currency = new Currency();
		currency.setDate("2024-05-17");
		currency.setRate(1.5);
		currency.setCurrency("USD");
		currencyRepository.save(currency);

		// Test finding by date and currency
		Currency foundCurrency = currencyRepository.findByDateAndCurrency("2024-05-17", "USD");
		assertNotNull(foundCurrency);
		assertEquals("2024-05-17", foundCurrency.getDate());
		assertEquals("USD", foundCurrency.getCurrency());
	}

	@Test
	@Sql("/data-test.sql") // Load test data from SQL file
	public void testFindHistoryByCurrency() {
		// Test finding history by currency
		List<Currency> currencyHistory = currencyRepository.findHistoryByCurrency("USD");
		assertNotNull(currencyHistory);
		assertEquals(2, currencyHistory.size()); // Assuming there are two entries for USD in the test data
	}
}
