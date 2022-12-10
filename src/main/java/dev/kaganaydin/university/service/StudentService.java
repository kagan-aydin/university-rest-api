package dev.kaganaydin.university.service;

import dev.kaganaydin.university.model.Student;
import dev.kaganaydin.university.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(String name, String surname) {
        if (name == null && surname == null){
            return studentRepository.findAll();
        } else if (name != null && surname == null) {
            return studentRepository.findByName(name);
        } else if (name == null && surname != null) {
            return studentRepository.findBySurname(surname);
        } else {
            return studentRepository.findByNameAndSurname(name, surname);
        }
    }
	
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found!"));
    }

    public Student addStudent(Student newStudent) {
        Student student = studentRepository.save(newStudent);
        return student;
    }
	
    public void updateStudent(Long id, Student newStudent){
        Student oldStudent = getStudentById(id);
        newStudent.setId(oldStudent.getId());
        newStudent.setUpdateDate(new Date());
        studentRepository.save(newStudent);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        student.setDeleteDate(new Date());
        studentRepository.save(student);
    }
}
