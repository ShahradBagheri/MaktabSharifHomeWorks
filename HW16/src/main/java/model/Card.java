package model;

import enumeration.BankName;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    private String cardNumber;

    private String cvv2;

    @Column(name = "bank_name")
    @Enumerated(value = EnumType.STRING)
    private BankName bankName;

    private LocalDate expirationDate;
}
