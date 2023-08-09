package secondQuestion.model;

import lombok.*;
import secondQuestion.enums.TeacherTier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Teacher extends Person{

    @Column(unique = true)
    private String teacherId;

    private String major;

    @Enumerated(EnumType.STRING)
    private TeacherTier teacherTier;

    private double salary;
}
