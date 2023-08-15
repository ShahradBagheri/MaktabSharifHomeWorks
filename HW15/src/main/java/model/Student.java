package model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "student")
@Entity
public class Student extends BaseUser {

    @Column(name = "student_university_id")
    private String studentId;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, mappedBy = "student")
    private Set<Course> courseSet;
}
