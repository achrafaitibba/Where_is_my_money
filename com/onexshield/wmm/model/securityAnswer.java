package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "security_answer")
public class securityAnswer {
    @Id
    @Column(name = "answer_id")
    private UUID answerId = UUID.randomUUID();

    @NotNull
    private String answer;

    @NotNull
    @ManyToOne
    private securityQuestion question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_id", referencedColumnName = "account_id")
    private account account;

}
