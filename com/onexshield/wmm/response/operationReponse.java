package com.onexshield.wmm.response;

import java.util.UUID;

public record operationReponse(
        UUID operationId,
        double amount,
        String operationType,
        String description,
        String operationDate,
        Integer accountId
) {
}
