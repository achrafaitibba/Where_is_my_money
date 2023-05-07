package com.onexshield.wmm.controller;

import com.onexshield.wmm.model.securityAnswer;
import com.onexshield.wmm.model.securityQuestion;
import com.onexshield.wmm.service.accountService;
import com.onexshield.wmm.service.securityQuestionInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class securityQuestionController {
    @Autowired
    securityQuestionInitializer securityQuestionInitializer;
    @Autowired
    accountService accountService;

    @GetMapping()
    public List<securityQuestion> getAll(){
        return securityQuestionInitializer.getAll();
    }

}
