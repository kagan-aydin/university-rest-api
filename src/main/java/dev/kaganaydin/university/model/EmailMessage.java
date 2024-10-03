package dev.kaganaydin.university.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    private String email;
    private String message;
    private String data;
}
