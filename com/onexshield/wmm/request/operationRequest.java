package com.onexshield.wmm.request;


public record operationRequest(
        double amount,
        String operationType,
        String description,
        Integer accountId
) {
}
