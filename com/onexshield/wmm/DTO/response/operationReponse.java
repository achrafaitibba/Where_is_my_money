package com.onexshield.wmm.DTO.response;

import java.util.UUID;

public record operationReponse(
        UUID operationId,
        double amount,
        String operationType,
        String description,
        String operationDate,
        UUID accountId
) {
}
