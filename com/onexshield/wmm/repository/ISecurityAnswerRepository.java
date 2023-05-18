package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.securityAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ISecurityAnswerRepository extends JpaRepository<securityAnswer, Integer> {

    securityAnswer findByAccount_EmailAndQuestionQuestionId(String accountEmail, Integer QuestionId);

    @Modifying
    @Query("""
update securityAnswer sa set sa.answer = ?3 , sa.question.questionId = ?4 where sa.answerId = ?1 and sa.account.accountId = ?2
""")
    int updateByAccount_AccountIdAndAnswerId(Integer anwerId,
                                             Integer accountId,
                                             String answer,
                                             Integer questionId);
}
