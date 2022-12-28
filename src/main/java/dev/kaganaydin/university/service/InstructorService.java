package dev.kaganaydin.university.service;

import dev.kaganaydin.university.model.Instructor;
import dev.kaganaydin.university.repository.InstructorRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private DepartmentService departmentService;
    public List<Instructor> getAllInstructors(String name, String surname) {
        if (name == null && surname == null){
            return instructorRepository.findAll();
        } else if (name != null && surname == null) {
            return instructorRepository.findByName(name);
        } else if (name == null) {
            return instructorRepository.findBySurname(surname);
        } else {
            return instructorRepository.findByNameAndSurname(name, surname);
        }
    }
	
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found!"));
    }

    public Instructor addInstructor(Instructor newInstructor) {
        departmentService.getDepartmentById(newInstructor.getDepartmentId());
        return instructorRepository.save(newInstructor);
    }
	
    public void updateInstructor(Long id, Instructor newInstructor){
        Instructor oldInstructor = getInstructorById(id);
        newInstructor.setId(oldInstructor.getId());
        newInstructor.setUpdateDate(new Date());
        instructorRepository.save(newInstructor);
    }

    public void deleteInstructor(Long id) {
        getInstructorById(id);
        instructorRepository.deleteById(id);
    }
}
