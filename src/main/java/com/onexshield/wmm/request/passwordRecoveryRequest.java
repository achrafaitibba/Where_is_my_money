package com.onexshield.wmm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class passwordRecoveryRequest {
    private String email;
    private List<securityAnswerRegisterRequest> securityAnswers;
    private String newPassword;
}
