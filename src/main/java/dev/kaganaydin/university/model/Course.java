package dev.kaganaydin.university.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Date;

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
    private Date createDate = new Date();
    private Date updateDate;
    private Date deleteDate;
}
