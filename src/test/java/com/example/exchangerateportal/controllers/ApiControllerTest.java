package com.example.exchangerateportal.controllers;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetCurrency() throws Exception {
		mockMvc.perform(get("/currencies"))
				.andExpect(status().isOk())
				.andExpect(content().string(notNullValue()));
	}

	@Test
	public void testProcessAndSaveCurrencyData() throws Exception {
		mockMvc.perform(get("/api/currencies"))
				.andExpect(status().isOk())
				.andExpect(content().string(notNullValue()));
	}

	@Test
	public void testFindCurrencyGet() throws Exception {
		mockMvc.perform(get("/api/history/USD"))
				.andExpect(status().isOk())
				.andExpect(content().string(notNullValue()));
	}

	@Test
	public void testFindCurrencyPost() throws Exception {
		mockMvc.perform(post("/api/history/USD"))
				.andExpect(status().isOk())
				.andExpect(content().string(notNullValue()));
	}
}
