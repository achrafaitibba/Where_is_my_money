package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operation")
@Component
public class operation {

    @Id
    @NotNull
    @Column(name = "operation_id")
    private UUID operationId = UUID.randomUUID();

    @NotNull
    @Column(name = "amount")
    private double amount;

    @NotNull
    @Column(name = "transaction_date")
    private Date transactionDate = new Date();

    @NotNull
    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private operationType operationType;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    private account account;


}
