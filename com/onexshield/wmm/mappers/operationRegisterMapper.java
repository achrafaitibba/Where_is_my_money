package com.onexshield.wmm.mappers;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.model.operationType;

import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.response.operationReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class operationRegisterMapper implements Function<operationRequest, operation> {
    private final operation operation;

    //todo use builder here to follow the same pattern as accountMapper
    public operation apply(operationRequest operationRequest){
        account a = new account();
        a.setAccountId(operationRequest.getAccountId());
        operation.setOperationId(Math.abs(new Random().nextInt()));
        operation.setAmount(operationRequest.getAmount());
        operation.setOperationType(operationRequest.getOperationType());
        operation.setDescription(operationRequest.getDescription());
        operation.setTransactionDate(new Date());
        operation.setAccount(a);
        return operation;
    }

    public operationReponse operationToOperationResponse(operation operation){
        return new operationReponse(
                operation.getOperationId(),
                operation.getAmount(),
                operation.getOperationType(),
                operation.getDescription(),
                operation.getTransactionDate(),
                operation.getAccount().getAccountId()
        );
    }

}
