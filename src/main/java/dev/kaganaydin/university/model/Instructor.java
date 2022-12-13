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
public class Instructor {
    @Id
    @SequenceGenerator(
            name = "instructor_sequence",
            sequenceName = "instructor_sequence"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "instructor_sequence"
    )
    private Long id;
    private String name;
    private String surname;
    private String rank;
    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;
    private Date createDate = new Date();
    private Date updateDate;
    private Date deleteDate;
}
//@JoinColumn(name="instructor_id")