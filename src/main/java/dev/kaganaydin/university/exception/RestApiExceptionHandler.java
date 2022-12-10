package dev.kaganaydin.university.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestApiError> handleEntityNotFoundException(EntityNotFoundException ex) {
        RestApiError respApiError = new RestApiError(NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(respApiError, respApiError.getHttpStatus());
    }
}
