package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.securityAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISecurityAnswerRepository extends JpaRepository<securityAnswer, Integer> {

    securityAnswer findByAccount_EmailAndQuestionQuestionId(String accountEmail, Integer QuestionId);


}
