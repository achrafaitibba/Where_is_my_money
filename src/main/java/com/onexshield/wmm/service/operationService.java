package com.onexshield.wmm.service;

import com.onexshield.wmm.exception.requestException;
import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.repository.IOperationRepository;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.repository.operationRepositoryJDBC;
import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.request.operationStatsRequest;
import com.onexshield.wmm.response.operationReponse;
import com.onexshield.wmm.mappers.operationRegisterMapper;
import com.onexshield.wmm.response.operationStatsResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class operationService {

    private final IOperationRepository iOperationRepository;
    private final IAccountRepository accountRepository;
    private final operationRegisterMapper operationRegisterMapper;
    private final operationRepositoryJDBC operationStatsDao;
    public operationReponse createOperation(operationRequest operation){
        Optional<account> account1= Optional.ofNullable(accountRepository.findByAccountId(Long.valueOf(operation.getAccountId())));
        if(account1.isPresent()){
            return operationRegisterMapper.operationToOperationResponse(
                    iOperationRepository.save(
                            operationRegisterMapper.apply(operation)
                    )
            );
        }else {
            throw new requestException("Account doesn't exist", HttpStatus.NOT_FOUND);
        }

    }

    public List<operationReponse> getAllOperationsByAccount(Long id){
        List<operation> operations = iOperationRepository.findAllByAccount_AccountId(id);
        return operations
                .stream()
                .map(operation -> new operationReponse(
                        String.valueOf(operation.getOperationId()),
                        operation.getAmount(),
                        operation.getOperationType(),
                        operation.getDescription(),
                        operation.getTransactionDate(),
                        String.valueOf(operation.getAccount().getAccountId())))
                .collect(Collectors.toList());
    }

    public operationReponse updateOperation(operationRequest operationRequest, Long id) {
        operation operation = operationRegisterMapper.apply(operationRequest);
        operation.setOperationId(id);
        return operationRegisterMapper.operationToOperationResponse(iOperationRepository.save(operation));

    }

    public void deleteOperation(Long id) {
        iOperationRepository.deleteById(id);
    }

    public operationReponse getOperation(Long id) {
        return operationRegisterMapper.operationToOperationResponse(iOperationRepository.findById(id).orElseThrow());
    }

    public List<operationStatsResponse> getStats(Long id, operationStatsRequest request)throws Exception{
        return operationStatsDao.getStats(id, request);
    }
}
