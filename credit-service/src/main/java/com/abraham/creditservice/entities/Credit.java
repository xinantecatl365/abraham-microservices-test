package com.abraham.creditservice.entities;

import com.abraham.creditservice.models.CreditStatus;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "credits")
@EntityListeners(AuditingEntityListener.class)
public class Credit {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private float interestRate;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditStatus status;

    @Column(nullable = false)
    private Integer customerId;

//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;
//
//    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
//    private List<Payment> payments;

}
