package com.onexshield.wmm.responseDTO;

import java.util.UUID;

public record operationReponseDTO(
        double amount,
        String operationType,
        String description,
        UUID accountId
) {
}
