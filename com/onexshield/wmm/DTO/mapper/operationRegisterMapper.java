package com.onexshield.wmm.DTO.mapper;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.model.operationType;
import com.onexshield.wmm.DTO.request.operationRequest;
import com.onexshield.wmm.DTO.response.operationReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.function.Function;

@Service
public class operationRegisterMapper implements Function<operationRequest, operation> {

    @Autowired
    operation operation;

    public operation apply(operationRequest operationRequest){
        account a = new account();
        a.setAccountId(operationRequest.accountId());
        operation.setOperationId(UUID.randomUUID());
        operation.setAmount(operationRequest.amount());
        operation.setOperationType(operationType.valueOf(operationRequest.operationType()));
        operation.setDescription(operationRequest.description());
        operation.setAccount(a);
        return operation;
    }

    public operationReponse operationToOperationResponse(operation operation){
        return new operationReponse(
                operation.getOperationId(),
                operation.getAmount(),
                operation.getOperationType().toString(),
                operation.getDescription(),
                new SimpleDateFormat("yyyy-dd-MM").format(operation.getTransactionDate()),
                operation.getAccount().getAccountId()
        );
    }

}
