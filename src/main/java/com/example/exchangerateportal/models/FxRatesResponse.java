package com.example.exchangerateportal.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "FxRates")
public class FxRatesResponse {

	@JacksonXmlProperty(localName = "FxRate")
	private FxRate[] fxRates;

	public FxRate[] getFxRates() {
		return fxRates;
	}

	public void setFxRates(FxRate[] fxRates) {
		this.fxRates = fxRates;
	}
}

class FxRate {
	@JacksonXmlProperty(localName = "Dt")
	private String date;

	@JacksonXmlProperty(localName = "Tp")
	private String type;

	@JacksonXmlProperty(localName = "CcyAmt")
	private CcyAmt[] ccyAmts;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CcyAmt[] getCcyAmts() {
		return ccyAmts;
	}

	public void setCcyAmts(CcyAmt[] ccyAmts) {
		this.ccyAmts = ccyAmts;
	}
}

class CcyAmt {
	@JacksonXmlProperty(localName = "Ccy")
	private String currency;

	@JacksonXmlProperty(localName = "Amt")
	private double amount;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
