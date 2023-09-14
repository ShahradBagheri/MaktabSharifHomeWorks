package model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = "loan")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateToPay;

    private Double amount;

    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

}
