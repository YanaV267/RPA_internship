package by.fin.processing.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BadRequestException extends RuntimeException {
    private final Class<?> resourceClass;

    public Class<?> getResourceClass() {
        return resourceClass;
    }
}
