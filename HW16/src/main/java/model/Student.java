package model;

import enumeration.Degree;
import enumeration.UniversityType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "social_id", unique = true)
    private String socialId;

    @Column(name = "government_id", unique = true)
    private String governmentId;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "student_id", unique = true)
    private String studentId;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "university_type")
    @Enumerated(value = EnumType.STRING)
    private UniversityType universityType;

    @Column(name = "entrance_year")
    private Integer entranceYear;

    @Column(name = "degree")
    @Enumerated(value = EnumType.STRING)
    private Degree degree;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "student")
    private List<Loan> loan;

    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

    @OneToOne(cascade = CascadeType.ALL)
    private RentContract rentContract;

    @OneToOne(cascade = CascadeType.ALL)
    private Student spouse;
}
