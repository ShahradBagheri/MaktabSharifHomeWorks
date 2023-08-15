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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "term")
    private String term;

    @Column(name = "score")
    private Float score;

    @Column(name = "units")
    private int units;
}
