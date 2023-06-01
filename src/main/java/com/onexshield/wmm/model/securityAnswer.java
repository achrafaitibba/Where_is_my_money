package com.onexshield.wmm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "security_answer")
public class securityAnswer {
    @Id
    @Column(name = "answer_id")
    @GeneratedValue
    private Long answerId;

    @NotNull
    private String answer;

    @NotNull
    @ManyToOne
    private securityQuestion question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_id", referencedColumnName = "account_id")
    @JsonBackReference
    private account account;

}
