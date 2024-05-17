package com.example.exchangerateportal.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FxRatesService {
	public String getFxRates() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
		String response = restTemplate.getForObject(url, String.class);

		try {
			String json = XML.toJSONObject(response).toString();
			JSONArray jsonArray = new JSONArray();

			JSONObject jsonObject = new JSONObject(json);
			JSONObject fxRates = jsonObject.getJSONObject("FxRates");
			JSONArray fxRatesArray = fxRates.getJSONArray("FxRate");

			for (int i = 0; i < fxRatesArray.length(); i++) {
				JSONObject fxRateObject = fxRatesArray.getJSONObject(i);
				String date = fxRateObject.getString("Dt");
				JSONArray ccyAmtArray = fxRateObject.getJSONArray("CcyAmt");

				for (int j = 0; j < ccyAmtArray.length(); j++) {
					JSONObject ccyAmtObject = ccyAmtArray.getJSONObject(j);
					String currency = ccyAmtObject.getString("Ccy");
					Double rate = ccyAmtObject.getDouble("Amt");

					if (!currency.equals("EUR")) {
						JSONObject newJson = new JSONObject();
						newJson.put("date", date);
						newJson.put("rate", rate);
						newJson.put("currency", currency);
						jsonArray.put(newJson);
					}
				}
			}

			return jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
