package com.onexshield.wmm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class operationRequest {
    private double amount ;
    private String operationType ;
    private String description ;
    private Integer accountId;
}
