package dev.kaganaydin.university.controller;

import dev.kaganaydin.university.model.Department;
import dev.kaganaydin.university.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(@RequestParam(required = false) String name){
        return new ResponseEntity<>(departmentService.getAllDepartments(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id),OK);
    }

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department newDepartment) {
        Department department = departmentService.addDepartment(newDepartment);
        return new ResponseEntity<>(department,CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer id, @RequestBody Department newDepartment) {
        departmentService.updateDepartment(id, newDepartment);
        return new ResponseEntity<>(newDepartment,OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
