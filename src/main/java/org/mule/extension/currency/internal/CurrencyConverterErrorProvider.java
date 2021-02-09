package org.mule.extension.currency.internal;

import java.util.HashSet;
import java.util.Set;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public class CurrencyConverterErrorProvider implements ErrorTypeProvider {
    @Override
    public Set<ErrorTypeDefinition> getErrorTypes() {
        HashSet<ErrorTypeDefinition> errors = new HashSet<ErrorTypeDefinition>();
        errors.add(CurrencyConverterErrorType.INVALID_REQUEST);
        return errors;
    }
}
