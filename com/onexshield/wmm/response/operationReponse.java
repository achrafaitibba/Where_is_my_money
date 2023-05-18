package com.onexshield.wmm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class operationReponse {
    private Integer operationId;
    private double amount;
    private String operationType;
    private String description;
    private String operationDate;
    private Integer accountId;
}
