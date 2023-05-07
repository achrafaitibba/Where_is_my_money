package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "security_answer")
public class securityAnswer {
    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Integer answerId;

    @NotNull
    private String answer;

    @NotNull
    @ManyToOne
    private securityQuestion question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_id", referencedColumnName = "account_id")
    private account account;

}
