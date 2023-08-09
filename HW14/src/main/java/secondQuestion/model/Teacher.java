package secondQuestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import secondQuestion.enums.TeacherTier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends Person{

    @Column(unique = true)
    private String teacherId;

    private String major;

    @Enumerated(EnumType.STRING)
    private TeacherTier teacherTier;

    private double salary;
}
