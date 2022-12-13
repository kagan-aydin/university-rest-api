package dev.kaganaydin.university.controller;

import dev.kaganaydin.university.model.Student;
import dev.kaganaydin.university.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name, @RequestParam(required = false) String surname){
        return new ResponseEntity<>(studentService.getAllStudents(name, surname), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id),OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student newStudent) {
        studentService.addStudent(newStudent);
        return new ResponseEntity<>(newStudent,CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student newStudent) {
        studentService.updateStudent(id, newStudent);
        return new ResponseEntity<>(newStudent,OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
