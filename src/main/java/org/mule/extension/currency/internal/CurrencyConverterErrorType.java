package org.mule.extension.currency.internal;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public enum CurrencyConverterErrorType implements ErrorTypeDefinition<CurrencyConverterErrorType> {
    INVALID_REQUEST,
    EMPTY_REQUEST
}