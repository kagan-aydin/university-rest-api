package dev.kaganaydin.university.service;

import dev.kaganaydin.university.dto.CourseStudentDto;
import dev.kaganaydin.university.model.Course;
import dev.kaganaydin.university.model.EmailMessage;
import dev.kaganaydin.university.model.Student;
import dev.kaganaydin.university.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final ProducerService producerService;

    public List<Course> getAllCourses(String name) {
        if (name == null){
            return courseRepository.findAll();
        } else {
            return courseRepository.findByName(name);
        }
    }
	
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found!"));
    }

    public Course addCourse(Course newCourse) {
        instructorService.getInstructorById(newCourse.getInstructorId());
        Course course = courseRepository.save(newCourse);
        log.debug("Course added with id:{}",course.getId());
        return course;
    }

    public void updateCourse(Long id, Course newCourse){
        Course oldCourse = getCourseById(id);
        newCourse.setId(oldCourse.getId());
        newCourse.setUpdateDate(new Date());
        courseRepository.save(newCourse);
        log.debug("Course with id:{} updated ",id);
    }

    public void deleteCourse(Long id) {
        getCourseById(id);
        courseRepository.deleteById(id);
        log.debug("Course with id:{} deleted",id);
    }
    public void addStudentToCourse(CourseStudentDto courseStudentDto) {
        Course course = getCourseById(courseStudentDto.getCourseId());
        Student student = studentService.getStudentById(courseStudentDto.getStudentId());
        course.getStudents().add(student);
        try {
            courseRepository.save(course);
            String msg = "Student with id:" + courseStudentDto.getStudentId() + " added to course with id:" + courseStudentDto.getCourseId();
            log.debug(msg);
            EmailMessage message = new EmailMessage(student.getEmail(), msg, null);
            producerService.sendMessage(message);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Student has been already added to this course!");
        }
    }

    public void deleteStudentFromCourse(CourseStudentDto courseStudentDto) {
        Course course = getCourseById(courseStudentDto.getCourseId());
        Student student = studentService.getStudentById(courseStudentDto.getStudentId());
        Student result = course.getStudents().stream().filter(st -> st.getId().equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Student not found for this course!"));
        course.getStudents().remove(student);
        courseRepository.save(course);
        log.debug("Student with id:{} deleted from course with id:{}",courseStudentDto.getStudentId(),courseStudentDto.getCourseId());
    }
}
