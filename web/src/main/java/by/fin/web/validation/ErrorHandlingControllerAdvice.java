package by.fin.web.validation;

import by.fin.processing.dto.ServerRateDto;
import by.fin.processing.exception.BadRequestException;
import by.fin.processing.exception.NoDataFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

import static by.fin.processing.exception.ExceptionResponseMessage.INVALID_CURRENCY_TYPE;

@RestControllerAdvice
@Slf4j
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(violation -> new Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .toList();
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onBadRequestException(BadRequestException e) {
        return new ValidationErrorResponse(List.of(
                new Violation(e.getResourceClass().getName(), e.getMessage())));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMissingRequestParameterException(MissingServletRequestParameterException e) {
        return new ValidationErrorResponse(List.of(
                new Violation(e.getParameterName(), e.getMessage())));
    }

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ValidationErrorResponse handleNoDataFoundException(NoDataFoundException e) {
        return new ValidationErrorResponse(List.of(
                new Violation(e.getResourceClass().getName(), e.getParameters())));
    }

    @ExceptionHandler({HttpServerErrorException.class, HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleNoDataFoundException(HttpStatusCodeException e) {
        return new ValidationErrorResponse(List.of(
                new Violation(ServerRateDto.class.getName(), INVALID_CURRENCY_TYPE)));
    }
}
