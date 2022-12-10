package dev.kaganaydin.university.repository;

import dev.kaganaydin.university.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
}
