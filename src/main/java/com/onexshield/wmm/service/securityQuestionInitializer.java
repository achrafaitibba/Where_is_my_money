package com.onexshield.wmm.service;



import com.onexshield.wmm.model.securityQuestion;
import com.onexshield.wmm.repository.ISecurityQuestionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class securityQuestionInitializer {

    private final ISecurityQuestionRepository iSecurityQuestionRepository;

    @PostConstruct
    public void initiateSecurityQuestions(){
        securityQuestion question1 = new securityQuestion();
        question1.setQuestion("What was the name of your first pet?");
        securityQuestion question2 = new securityQuestion();
        question2.setQuestion("In what city were you born?");
        securityQuestion question3 = new securityQuestion();
        question3.setQuestion("What is your favorite movie?");
        securityQuestion question4 = new securityQuestion();
        question4.setQuestion("What was your high school name?");
        securityQuestion question5 = new securityQuestion();
        question5.setQuestion("What is your mother's last name?");
        securityQuestion question6 = new securityQuestion();
        question6.setQuestion("What is your favorite food?");
        securityQuestion question7 = new securityQuestion();
        question7.setQuestion("What is the name of your favorite teacher?");
        securityQuestion question8 = new securityQuestion();
        question8.setQuestion("What is your favorite book?");
        iSecurityQuestionRepository.save(question1);
        iSecurityQuestionRepository.save(question2);
        iSecurityQuestionRepository.save(question3);
        iSecurityQuestionRepository.save(question4);
        iSecurityQuestionRepository.save(question5);
        iSecurityQuestionRepository.save(question6);
        iSecurityQuestionRepository.save(question7);
        iSecurityQuestionRepository.save(question8);
    }

    public List<securityQuestion> getAll() {
        return iSecurityQuestionRepository.findAll();
    }
}
