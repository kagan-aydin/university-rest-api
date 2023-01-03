package dev.kaganaydin.university.service;

import dev.kaganaydin.university.model.Instructor;
import dev.kaganaydin.university.model.Student;
import dev.kaganaydin.university.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(String name, String surname) {
        if (name == null && surname == null){
            return studentRepository.findAll();
        } else if (name != null && surname == null) {
            return studentRepository.findByName(name);
        } else if (name == null) {
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
        log.debug("Student added with id:{}",student.getId());
        return student;
    }
	
    public void updateStudent(Long id, Student newStudent){
        Student oldStudent = getStudentById(id);
        newStudent.setId(oldStudent.getId());
        newStudent.setUpdateDate(new Date());
        studentRepository.save(newStudent);
        log.debug("Student with id:{} updated",id);
    }

    public void deleteStudent(Long id) {
        getStudentById(id);
        studentRepository.deleteById(id);
        log.debug("Student with id:{} deleted",id);
    }
}
