package dev.kaganaydin.university.repository;

import dev.kaganaydin.university.model.Instructor;
import dev.kaganaydin.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findBySurname(String surname);
    List<Student> findByNameAndSurname(String name, String surname);
}
