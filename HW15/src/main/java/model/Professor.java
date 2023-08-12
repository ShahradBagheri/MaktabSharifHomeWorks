package model;

import enumeration.ProfessorTier;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "professor")
@Entity
public class Professor extends User{

    private ProfessorTier professorTier;

    private Set<Course> courseSet;
}
