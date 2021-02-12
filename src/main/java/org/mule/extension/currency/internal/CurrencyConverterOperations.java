package org.mule.extension.currency.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.exception.ModuleException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */

public class CurrencyConverterOperations {
	private static String CURRENCY_API_URL = "ExchangerateURL";
	
	@Parameter
	@Example("1000")
	@DisplayName("Amount")
	@Expression(org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED)
	private Double amount;
	/**
	 * Example of a simple operation that receives an amount in double and returns
	 * equivalent amount in US dollar that will be set on the payload.
	 */
	@MediaType(value = ANY, strict = false)
	@DisplayName("INR To USD")
	@Throws(CurrencyConverterErrorProvider.class)
	public Double inrToUsd(@Config CurrencyConverterConfiguration config) {
		Double result = null;
		CURRENCY_API_URL = config.getCurrencyAPIUrl()+"?apikey=" + config.getApiKey();
		try {
			result = amount / getExchangeRate("INR");
		} catch (IOException e) {
			throw new ModuleException(CurrencyConverterErrorType.INVALID_REQUEST, e);
		}
		return result;
	}

	private static Double getExchangeRate(String currencyCode) throws IOException {
		URL obj = new URL(CURRENCY_API_URL);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("GET");
		StringBuilder response = new StringBuilder();
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
		} else {
			System.out.println("GET request failed.");
		}
		String[] arrOfStr = response.toString().replaceAll("\"", "").split(",");

		Double exchangeRate = null;
		for (String a : arrOfStr) {
			if (a.contains(currencyCode))
				exchangeRate = Double.parseDouble(a.split(":")[1]);
		}
		return exchangeRate;
	}

}
