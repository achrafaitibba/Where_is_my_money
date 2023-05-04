package com.onexshield.wmm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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

    @OneToOne
    private account account;


}
