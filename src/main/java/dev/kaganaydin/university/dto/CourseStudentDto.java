package dev.kaganaydin.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseStudentDto {
    private Long courseId;
    private Long studentId;
}
