package dev.kaganaydin.university.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
@Data
public class RestApiError {

    private HttpStatus status;
    private String message;


    public RestApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
