package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.securityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISercurityQuestionRepository extends JpaRepository<securityQuestion,Integer> {

}
