package dev.kaganaydin.university.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> messages = new ArrayList<String>();
        for (ObjectError error: exception.getBindingResult().getAllErrors()) {
            messages.add(error.getDefaultMessage());
        }
        RestApiError restApiError = new RestApiError(HttpStatus.BAD_REQUEST, messages.stream().collect(Collectors.joining(" ")));
        return new ResponseEntity<>(restApiError, restApiError.getHttpStatus());
    }

}
