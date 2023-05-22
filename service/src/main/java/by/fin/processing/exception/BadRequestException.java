package by.fin.processing.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BadRequestException extends RuntimeException {
    private final Class<?> resourceClass;

    public BadRequestException(String message, Class<?> resourceClass) {
        super(message);
        this.resourceClass = resourceClass;
    }

    public Class<?> getResourceClass() {
        return resourceClass;
    }
}
