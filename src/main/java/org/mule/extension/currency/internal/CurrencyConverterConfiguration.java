package org.mule.extension.currency.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(CurrencyConverterOperations.class)
public class CurrencyConverterConfiguration {

	  @Parameter
	  @DisplayName("Currency API URL")
	  @Example("https://api.currencyfreaks.com/latest")
	  @Placement(order = 1)
	  private String currencyAPIUrl;

	  @Parameter
	  @DisplayName("API Key")
	  @Example("62d0d90e107949bd86e03a36f0f168da")
	  @Placement(order = 2)
	  private String apiKey;

	  public String getCurrencyAPIUrl() {
	    return currencyAPIUrl;
	  }

	  public String getApiKey() {
	    return apiKey;
	  }
}
