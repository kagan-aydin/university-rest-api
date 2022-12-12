package dev.kaganaydin.university.service;

import dev.kaganaydin.university.model.Course;
import dev.kaganaydin.university.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository CourseRepository;
    private final InstructorService instructorService;

    public List<Course> getAllCourses(String name) {
        if (name == null){
            return CourseRepository.findAll();
        } else {
            return CourseRepository.findByName(name);
        }
    }
	
    public Course getCourseById(Long id) {
        return CourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found!"));
    }

    public Course addCourse(Course newCourse) {
        Course course = CourseRepository.save(newCourse);
        return course;
    }

    public void updateCourse(Long id, Course newCourse){
        Course oldCourse = getCourseById(id);
        newCourse.setId(oldCourse.getId());
        newCourse.setUpdateDate(new Date());
        CourseRepository.save(newCourse);
    }

    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        course.setDeleteDate(new Date());
        CourseRepository.save(course);
    }
}
