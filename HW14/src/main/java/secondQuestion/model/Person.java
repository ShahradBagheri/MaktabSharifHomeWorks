package secondQuestion.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    private String firstname;

    @Column()
    private String lastname;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthdate;
}
