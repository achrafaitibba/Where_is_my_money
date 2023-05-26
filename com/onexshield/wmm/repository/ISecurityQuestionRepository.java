package com.onexshield.wmm.repository;



import com.onexshield.wmm.model.securityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISecurityQuestionRepository extends JpaRepository<securityQuestion,Integer> {

}
