package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class operation {

    @Id
    @NotNull
    @Column(name = "operation_id")
    private Long operationId;

    @NotNull
    private double amount;

    @Column(name = "transaction_date")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @NotNull
    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private operationType operationType;

    @NotNull
    private String description;

    @NotNull
    @ManyToOne
    private account account;


}
