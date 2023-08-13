package model;

import enumeration.ProfessorTier;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "professor")
@Entity
public class Professor extends User{

    @Enumerated(value = EnumType.STRING)
    private ProfessorTier professorTier;

    private Double baseSalary;

    @OneToMany
    private Set<Course> courseSet;

    public double calculateSalary(String  term){

        long courseCount = courseSet.stream().filter(course -> course.getTerm().equals(term)).count();

        if (this.professorTier == ProfessorTier.NORMAL)
            return courseCount * 1000000;
        return baseSalary + courseCount * 1000000;
    }
}
