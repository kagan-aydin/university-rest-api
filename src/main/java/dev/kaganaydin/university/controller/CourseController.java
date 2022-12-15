package dev.kaganaydin.university.controller;

import dev.kaganaydin.university.dto.CourseStudentDto;
import dev.kaganaydin.university.model.Course;
import dev.kaganaydin.university.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String name){
        return new ResponseEntity<>(courseService.getAllCourses(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(courseService.getCourseById(id),OK);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course newCourse) {
        Course course = courseService.addCourse(newCourse);
        return new ResponseEntity<>(course,CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course newCourse) {
        courseService.updateCourse(id, newCourse);
        return new ResponseEntity<>(newCourse,OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/students")
    public ResponseEntity<Void> addStudentToCourse(@RequestBody CourseStudentDto courseStudentDto) {
        courseService.addStudentToCourse(courseStudentDto);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/students")
    public ResponseEntity<Void> deleteStudentFromCourse(@RequestBody CourseStudentDto courseStudentDto) {
        courseService.deleteStudentFromCourse(courseStudentDto);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
