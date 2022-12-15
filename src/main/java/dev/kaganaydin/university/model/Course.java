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
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String code;
    private String name;
    private Integer credit;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="department_id", nullable = false, insertable = false, updatable = false)
    private Department department;
    private Integer department_id;
    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="instructor_id", nullable = false, insertable = false, updatable = false)
    private Instructor instructor;
    private Long instructor_id;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "student_id"})
    )
    private List<Student> students;
    private String rank;
    private Date createDate = new Date();
    private Date updateDate;
    private Date deleteDate;
}
