package com.onexshield.wmm.mappers;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;

import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.response.operationReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class operationRegisterMapper implements Function<operationRequest, operation> {
    private final operation operation;

    public operation apply(operationRequest operationRequest){
        account a = new account();
        a.setAccountId(operationRequest.getAccountId());
        return operation.builder()
                .operationId(Math.abs(new Random().nextLong()))
                .amount(operationRequest.getAmount())
                .operationType(operationRequest.getOperationType())
                .description(operationRequest.getDescription())
                .transactionDate(new Date())
                .account(a)
                .build();
    }

    public operationReponse operationToOperationResponse(operation operation){
        return new operationReponse(
                String.valueOf(operation.getOperationId()),
                operation.getAmount(),
                operation.getOperationType(),
                operation.getDescription(),
                operation.getTransactionDate(),
                String.valueOf(operation.getAccount().getAccountId())
        );
    }

}
