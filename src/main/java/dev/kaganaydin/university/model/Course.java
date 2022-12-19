package dev.kaganaydin.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE course SET delete_date = current_timestamp WHERE id=?")
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
    @NotNull(message = "Code is required!")
    private String code;
    @NotNull(message = "Name is required!")
    private String name;
    @NotNull(message = "Credit is required!")
    private Integer credit;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="departmentId", nullable = false, insertable = false, updatable = false)
    private Department department;
    @NotNull(message = "Department Id is required!")
    private Integer departmentId;
    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="instructorId", nullable = false, insertable = false, updatable = false)
    private Instructor instructor;
    @NotNull(message = "Instructor Id is required!")
    private Long instructorId;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "student_id"})
    )
    private List<Student> students;
    private Date createDate = new Date();
    private Date updateDate;
    private Date deleteDate;
}
