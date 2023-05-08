package com.onexshield.wmm.requestDTO;

import java.util.UUID;

public record operationRequestDTO(
        double amount,
        String operationType,
        String description,
        UUID accountId
) {
}
