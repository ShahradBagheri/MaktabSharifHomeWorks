package secondQuestion.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class Student extends Person {

    @Column(unique = true)
    private String studentId;

    private String major;

    private String admissionYear;

}
