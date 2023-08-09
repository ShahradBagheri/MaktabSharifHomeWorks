package secondQuestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends Person {

    @Column(unique = true)
    private String studentId;

    private String major;

    private String admissionYear;
}
