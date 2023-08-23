package model;

import enumeration.ProfessorTier;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "courseSet")
@Table(name = "professor")
@Entity
public class Professor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Enumerated(value = EnumType.STRING)
    private ProfessorTier professorTier;

    @Column(name = "base_salary")
    private Double baseSalary;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, mappedBy = "professor")
    private Set<Course> courseSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public double calculateSalary(Long  term){

        long courseCount = courseSet.stream().filter(course -> course.getTerm().equals(term)).map(Course::getUnits).mapToInt(Integer::valueOf).sum();

        if (this.professorTier == ProfessorTier.NORMAL)
            return courseCount * 1000;
        return baseSalary + courseCount * 1000;
    }
}
