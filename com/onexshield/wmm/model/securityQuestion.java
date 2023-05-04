package com.onexshield.wmm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "security_question")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class securityQuestion {
    @Id
    @GeneratedValue
    private Integer questionId;

    private String question;


}
