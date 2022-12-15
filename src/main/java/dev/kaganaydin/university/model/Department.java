package dev.kaganaydin.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Where(clause="delete_date is null")
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "department_sequence"
    )
    private Integer id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Instructor> instructors;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Student> students;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Student> courses;
    private Date createDate = new Date();
    private Date updateDate;
    private Date deleteDate;
}