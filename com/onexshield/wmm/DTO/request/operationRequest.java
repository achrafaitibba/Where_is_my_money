package com.onexshield.wmm.DTO.request;

import java.util.UUID;

public record operationRequest(
        double amount,
        String operationType,
        String description,
        UUID accountId
) {
}
