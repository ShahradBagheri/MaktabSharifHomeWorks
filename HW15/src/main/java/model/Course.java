package model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Course {

    @Id

    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Professor professor;

    private String term;
}
