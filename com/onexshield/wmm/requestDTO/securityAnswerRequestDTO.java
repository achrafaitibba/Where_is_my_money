package com.onexshield.wmm.requestDTO;

import java.util.UUID;

public record securityAnswerRequestDTO(
        UUID answerId,
        String answer,
        Integer questionId
) {
}
