package com.onexshield.wmm.DTOMapper;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.model.operationType;
import com.onexshield.wmm.requestDTO.operationRequestDTO;
import com.onexshield.wmm.responseDTO.operationReponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;

@Service
public class operationRegisterMapper implements Function<operationRequestDTO, operation> {

    @Autowired
    operation operation;

    public operation apply(operationRequestDTO operationRequestDTO){
        account a = new account();
        a.setAccountId(operationRequestDTO.accountId());
        operation.setOperationId(UUID.randomUUID());
        operation.setAmount(operationRequestDTO.amount());
        operation.setOperationType(operationType.valueOf(operationRequestDTO.operationType()));
        operation.setDescription(operationRequestDTO.description());
        operation.setAccount(a);
        return operation;
    }

    public operationReponseDTO operationToOperationResponse(operation operation){
        return new operationReponseDTO(
                operation.getAmount(),
                operation.getOperationType().toString(),
                operation.getDescription(),
                operation.getAccount().getAccountId()
        );
    }

}
