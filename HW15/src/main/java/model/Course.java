package model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "name")
    private String name;

    @Column(name = "term")
    private Long term;

    @Column(name = "units")
    private int units;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", professor=" + professor.getFirstname() +
                ", name='" + name + '\'' +
                ", term=" + term +
                ", units=" + units +
                '}';
    }
}
