package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operation")
public class operation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "operation_id")
    private Integer id;

    @NotNull
    @Column(name = "amount")
    private double amount;

    @NotNull
    @Column(name = "transaction_date")
    private Date transactionDate;

    @NotNull
    @Column(name = "operation_type")
    private operationType operationType;

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private account account;

}
