package com.onexshield.wmm.model;

import jakarta.persistence.*;
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

    @OneToOne
    private securityQuestion securityQuestion;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "fk_account_id", referencedColumnName = "account_id")
    private account account;



}
