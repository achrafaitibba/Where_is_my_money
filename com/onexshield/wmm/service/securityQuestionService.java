package com.onexshield.wmm.service;

import com.onexshield.wmm.model.securityQuestion;
import com.onexshield.wmm.repository.ISercurityQuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class securityQuestionService {
    @Autowired
    ISercurityQuestionRepository iSercurityQuestionRepository;

    @PostConstruct
    public void initiateSecurityQuestions(){
        securityQuestion question1 = new securityQuestion();
        question1.setQuestion("What was the name of your first pet?");
        securityQuestion question2 = new securityQuestion();
        question2.setQuestion("In what city were you born?");
        securityQuestion question3 = new securityQuestion();
        question3.setQuestion("What is your favorite book or movie?");
        securityQuestion question4 = new securityQuestion();
        question4.setQuestion("What was your high school name?");
        securityQuestion question5 = new securityQuestion();
        question5.setQuestion("What is your mother's last name?");
        securityQuestion question6 = new securityQuestion();
        question6.setQuestion("What is your favorite food?");
        securityQuestion question7 = new securityQuestion();
        question7.setQuestion("What is the name of your favorite teacher?");
        iSercurityQuestionRepository.save(question1);
        iSercurityQuestionRepository.save(question2);
        iSercurityQuestionRepository.save(question3);
        iSercurityQuestionRepository.save(question4);
        iSercurityQuestionRepository.save(question5);
        iSercurityQuestionRepository.save(question6);
        iSercurityQuestionRepository.save(question7);
    }
}
