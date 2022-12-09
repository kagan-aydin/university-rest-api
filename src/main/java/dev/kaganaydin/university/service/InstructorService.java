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

    private final InstructorRepository InstructorRepository;

    public List<Instructor> getAllInstructors(String name) {
        if (name == null){
            return InstructorRepository.findAll();
        } else {
            return InstructorRepository.findByName(name);
        }
    }
	
    public Instructor getInstructorById(Long id) {
        return InstructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found!"));
    }

    public Instructor addInstructor(Instructor newInstructor) {
        Instructor instructor = InstructorRepository.save(newInstructor);
        return instructor;
    }
	
    public void updateInstructor(Long id, Instructor newInstructor){
        Instructor oldInstructor = getInstructorById(id);
        newInstructor.setId(oldInstructor.getId());
        newInstructor.setUpdateDate(new Date());
        InstructorRepository.save(newInstructor);
    }

    public void deleteInstructor(Long id) {
        Instructor instructor = getInstructorById(id);
        instructor.setDeleteDate(new Date());
        InstructorRepository.save(instructor);
    }
}
