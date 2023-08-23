package model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    @Column(name = "score")
    private Float score;

    @Override
    public String toString() {
        return "CourseStudent{" +
                "id=" + id +
                ", course=" + course.getName() +
                ", student=" + student.getUsername() +
                ", score=" + score +
                '}';
    }
}
