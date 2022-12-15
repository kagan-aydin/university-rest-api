package dev.kaganaydin.university.service;

import dev.kaganaydin.university.dto.CourseStudentDto;
import dev.kaganaydin.university.model.Course;
import dev.kaganaydin.university.model.Student;
import dev.kaganaydin.university.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    private final StudentService studentService;

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
        instructorService.getInstructorById(newCourse.getInstructor_id());
        Course course = courseRepository.save(newCourse);
        return course;
    }

    public void updateCourse(Long id, Course newCourse){
        Course oldCourse = getCourseById(id);
        newCourse.setId(oldCourse.getId());
        newCourse.setUpdateDate(new Date());
        courseRepository.save(newCourse);
    }

    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        course.setDeleteDate(new Date());
        courseRepository.save(course);
    }
    public void addStudentToCourse(CourseStudentDto courseStudentDto) {
        Course course = getCourseById(courseStudentDto.getCourseId());
        Student student = studentService.getStudentById(courseStudentDto.getStudentId());
        course.getStudents().add(student);
        try {
            courseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Student has been already added to this course!");
        }
    }

    public void deleteStudentFromCourse(CourseStudentDto courseStudentDto) {
        Course course = getCourseById(courseStudentDto.getCourseId());
        Student student = studentService.getStudentById(courseStudentDto.getStudentId());
        Student result = course.getStudents().stream().filter(st -> st.getId() == student.getId())
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Student not found for this course!"));
        course.getStudents().remove(student);
        courseRepository.save(course);
    }
}
