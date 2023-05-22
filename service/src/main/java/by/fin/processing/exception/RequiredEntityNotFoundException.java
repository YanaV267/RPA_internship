package by.fin.processing.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequiredEntityNotFoundException extends RuntimeException {
    private static final String DELIMITER = " = ";
    private final Class<?> resourceClass;
    private final String parameters;

    public RequiredEntityNotFoundException(String parameterType, Object parameter, Class<?> resourceClass) {
        super();
        this.parameters = parameterType + DELIMITER + parameter;
        this.resourceClass = resourceClass;
    }

    public Class<?> getResourceClass() {
        return resourceClass;
    }

    public String getParameters() {
        return parameters;
    }
}
