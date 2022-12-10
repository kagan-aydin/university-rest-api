package dev.kaganaydin.university.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
@Data
public class RestApiError {
    @JsonIgnore
    private HttpStatus httpStatus;
    private Date timestamp = new Date();
    private Integer status;
    private String error;
    private String message;

    public RestApiError(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
    }

}
