package dev.kaganaydin.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CourseStudentDto {
    @NotNull(message = "Course Id is required!")
    private Long courseId;
    @NotNull(message = "Student Id is required!")
    private Long studentId;
}
