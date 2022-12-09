package dev.kaganaydin.university.repository;

import dev.kaganaydin.university.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    List<Instructor> findByName(String name);
}
