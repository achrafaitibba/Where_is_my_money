package com.onexshield.wmm.response;

import com.onexshield.wmm.model.operationType;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class operationReponse {
    private String operationId;
    private double amount;
    private operationType operationType;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date operationDate;
    private String accountId;
}
