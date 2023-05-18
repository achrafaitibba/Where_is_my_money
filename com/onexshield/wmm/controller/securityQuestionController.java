package com.onexshield.wmm.controller;

import com.onexshield.wmm.model.securityQuestion;
import com.onexshield.wmm.service.securityQuestionInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class securityQuestionController {
    private final securityQuestionInitializer securityQuestionInitializer;
    @GetMapping
    public List<securityQuestion> getAll(){
        return securityQuestionInitializer.getAll();
    }

}
