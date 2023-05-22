package by.fin.processing.exception;

import static by.fin.processing.util.ParameterName.EXCHANGE_RATES;
import static by.fin.processing.util.ParameterName.WEEKENDS;

public class NoDataFoundException extends RuntimeException {
    private static final String DELIMITER = " = ";
    private static final String ALL_DATA_VALUE = "all";
    private final Class<?> resourceClass;
    private final String parameters;

    public NoDataFoundException(String parameters, Class<?> resourceClass) {
        super();
        if (WEEKENDS.equals(parameters) || EXCHANGE_RATES.equals(parameters)) {
            this.parameters = parameters + DELIMITER + ALL_DATA_VALUE;
        } else {
            this.parameters = parameters.replaceAll(DELIMITER.trim(), DELIMITER);
        }
        this.resourceClass = resourceClass;
    }

    public Class<?> getResourceClass() {
        return resourceClass;
    }

    public String getParameters() {
        return parameters;
    }
}
