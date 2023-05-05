package com.onexshield.wmm.controller;

import com.onexshield.wmm.model.securityQuestion;
import com.onexshield.wmm.service.securityQuestionInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class securityQuestionController {
    @Autowired
    securityQuestionInitializer securityQuestionInitializer;

    @GetMapping()
    public List<securityQuestion> getAll(){
        return securityQuestionInitializer.getAll();
    }
}
