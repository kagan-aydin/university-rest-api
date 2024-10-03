package dev.kaganaydin.university.model;

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
@SQLDelete(sql = "UPDATE student SET delete_date = current_timestamp WHERE id=?")
@Where(clause="delete_date is null")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @NotNull(message = "Name is required!")
    private String name;
    @NotNull(message = "Surname is required!")
    private String surname;
    @NotNull(message = "Email is required!")
    private String email;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="departmentId", nullable = false, insertable = false, updatable = false)
    private Department department;
    @NotNull(message = "Department Id is required!")
    private Integer departmentId;
    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
    private Date createDate = new Date();
    private Date updateDate;
    private Date deleteDate;
}
