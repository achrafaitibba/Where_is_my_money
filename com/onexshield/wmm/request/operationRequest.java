package com.onexshield.wmm.request;

import java.util.UUID;

public record operationRequest(
        double amount,
        String operationType,
        String description,
        Integer accountId
) {
}
