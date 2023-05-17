package com.onexshield.wmm.response;


public record operationReponse(
        Integer operationId,
        double amount,
        String operationType,
        String description,
        String operationDate,
        Integer accountId
) {
}
