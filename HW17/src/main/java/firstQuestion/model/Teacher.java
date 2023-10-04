package firstQuestion.model;

import firstQuestion.model.enums.TeacherTier;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Teacher extends Person{

    @Column(unique = true)
    private String teacherId;

    private String major;

    @Enumerated(EnumType.STRING)
    private TeacherTier teacherTier;

    private double salary;
}
