package dev.kaganaydin.university.service;

import dev.kaganaydin.university.model.Course;
import dev.kaganaydin.university.model.Department;
import dev.kaganaydin.university.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments(String name) {
        if (name == null){
            return departmentRepository.findAll();
        } else {
            return departmentRepository.findByName(name);
        }
    }
	
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found!"));
    }

    public Department addDepartment(Department newDepartment) {
        Department department = departmentRepository.save(newDepartment);
        log.debug("Department added with id:{}",department.getId());
        return department;
    }
	
    public void updateDepartment(Integer id, Department newDepartment){
        Department oldDepartment = getDepartmentById(id);
        newDepartment.setId(oldDepartment.getId());
        newDepartment.setUpdateDate(new Date());
        departmentRepository.save(newDepartment);
        log.debug("Department with id:{} updated",id);
    }

    public void deleteDepartment(Integer id) {
        getDepartmentById(id);
        departmentRepository.deleteById(id);
        log.debug("Department with id:{} deleted",id);
    }
}
