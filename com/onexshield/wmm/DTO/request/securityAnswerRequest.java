package com.onexshield.wmm.DTO.request;

import java.util.UUID;

public record securityAnswerRequest(
        UUID answerId,
        String answer,
        Integer questionId
) {
}
