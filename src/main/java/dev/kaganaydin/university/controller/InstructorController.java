package dev.kaganaydin.university.controller;

import dev.kaganaydin.university.model.Instructor;
import dev.kaganaydin.university.service.InstructorService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/instructors")
@AllArgsConstructor
public class InstructorController {

    private final InstructorService InstructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors(@RequestParam(required = false) String name, @RequestParam(required = false) String surname){
        return new ResponseEntity<>(InstructorService.getAllInstructors(name, surname), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(InstructorService.getInstructorById(id),OK);
    }

    @PostMapping
    public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor newInstructor) {
        InstructorService.addInstructor(newInstructor);
        return new ResponseEntity<>(newInstructor,CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor newInstructor) {
        InstructorService.updateInstructor(id, newInstructor);
        return new ResponseEntity<>(newInstructor,OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        InstructorService.deleteInstructor(id);
        return new ResponseEntity<>(OK);
    }

}
