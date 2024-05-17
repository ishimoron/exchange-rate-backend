package com.example.exchangerateportal.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FxRatesResponseTest {

	@Test
	public void testFxRatesResponse() {
		// Create sample data
		FxRate fxRate1 = new FxRate();
		fxRate1.setDate("2024-05-17");
		fxRate1.setType("Type1");
		CcyAmt ccyAmt1 = new CcyAmt();
		ccyAmt1.setCurrency("USD");
		ccyAmt1.setAmount(1.5);
		fxRate1.setCcyAmts(new CcyAmt[] { ccyAmt1 });

		FxRate fxRate2 = new FxRate();
		fxRate2.setDate("2024-05-18");
		fxRate2.setType("Type2");
		CcyAmt ccyAmt2 = new CcyAmt();
		ccyAmt2.setCurrency("EUR");
		ccyAmt2.setAmount(1.6);
		fxRate2.setCcyAmts(new CcyAmt[] { ccyAmt2 });

		FxRatesResponse fxRatesResponse = new FxRatesResponse();
		fxRatesResponse.setFxRates(new FxRate[] { fxRate1, fxRate2 });

		// Test getters
		FxRate[] fxRates = fxRatesResponse.getFxRates();
		assertNotNull(fxRates);
		assertEquals(2, fxRates.length);

		// Test individual FxRate objects
		assertEquals("2024-05-17", fxRates[0].getDate());
		assertEquals("Type1", fxRates[0].getType());
		assertNotNull(fxRates[0].getCcyAmts());
		assertEquals(1, fxRates[0].getCcyAmts().length);
		assertEquals("USD", fxRates[0].getCcyAmts()[0].getCurrency());
		assertEquals(1.5, fxRates[0].getCcyAmts()[0].getAmount());

		assertEquals("2024-05-18", fxRates[1].getDate());
		assertEquals("Type2", fxRates[1].getType());
		assertNotNull(fxRates[1].getCcyAmts());
		assertEquals(1, fxRates[1].getCcyAmts().length);
		assertEquals("EUR", fxRates[1].getCcyAmts()[0].getCurrency());
		assertEquals(1.6, fxRates[1].getCcyAmts()[0].getAmount());
	}
}
