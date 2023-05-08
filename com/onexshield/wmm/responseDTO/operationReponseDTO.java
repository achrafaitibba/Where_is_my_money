package com.onexshield.wmm.responseDTO;

import java.util.UUID;

public record operationReponseDTO(
        UUID operationId,
        double amount,
        String operationType,
        String description,
        String operationDate,
        UUID accountId
) {
}
