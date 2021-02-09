package org.mule.extension.currency.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.exception.ModuleException;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class CurrencyConverterOperations {

	@Parameter
	@Example("1000")
	@DisplayName("Amount")
	private int amount;

	/**
	 * Example of a simple operation that receives an amount in double and returns
	 * equivalent amount in US dollar that will be set on the payload.
	 */
	@MediaType(value = ANY, strict = false)
	@DisplayName("INR To USD")
	@Throws(CurrencyConverterErrorProvider.class)
	public Double inrToUsd(@Config CurrencyConverterConfiguration config) {

		try {
			if (config.getApiKey() != null) {
				System.out.println(config.getApiKey());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ModuleException(CurrencyConverterErrorType.INVALID_REQUEST, e);
			// e.printStackTrace();
		}
		return amount / 73.0;// get latest exchange rates
	}

	/**
	 * Example of a simple operation that receives an amount in double and returns
	 * equivalent amount in pound that will be set on the payload.
	 */

	@MediaType(value = ANY)
	@DisplayName("USD To GBP")
	@Throws(CurrencyConverterErrorProvider.class)
	public Double usdToGbp(@Config CurrencyConverterConfiguration config) {

		try {
			if (config.getApiKey() != null) {
				System.out.println(config.getApiKey());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ModuleException(CurrencyConverterErrorType.INVALID_REQUEST, e);
			// e.printStackTrace();
		}

		return amount * 88.0; // get latest exchange rates

	}

}
