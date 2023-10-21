package com.example.internetbanking.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    private BigDecimal amount;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
