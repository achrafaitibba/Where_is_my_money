package com.onexshield.wmm.controller;

import com.onexshield.wmm.model.securityQuestion;
import com.onexshield.wmm.service.securityQuestionInitializer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Tag(name = "Question")
public class securityQuestionController {
    private final securityQuestionInitializer securityQuestionInitializer;
    @Operation(
            summary = "Security questions",
            description = "This endpoint returns all the security answers available in our api<br>" +
                    "It doesn't require any request body or params<br>" +
                    "It doesn't require authentication"
    )
    @GetMapping
    public List<securityQuestion> getAll(){
        return securityQuestionInitializer.getAll();
    }

}
