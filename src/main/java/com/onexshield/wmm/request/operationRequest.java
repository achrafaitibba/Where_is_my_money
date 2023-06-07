package com.onexshield.wmm.request;

import com.onexshield.wmm.model.operationType;

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
    private operationType operationType ;
    private String description ;
    private String accountId;
}
