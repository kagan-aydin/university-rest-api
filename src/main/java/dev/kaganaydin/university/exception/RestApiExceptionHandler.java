package dev.kaganaydin.university.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestApiError> handleEntityNotFoundException(EntityNotFoundException ex) {
        RestApiError restApiError = new RestApiError(NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(restApiError, restApiError.getHttpStatus());
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<RestApiError> handleEntityExistsException(EntityExistsException ex) {
        RestApiError restApiError = new RestApiError(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(restApiError, restApiError.getHttpStatus());
    }

}
