package com.onexshield.wmm.service;

import com.onexshield.wmm.exception.requestException;
import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.model.operationType;
import com.onexshield.wmm.repository.IOperationRepository;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.request.columnFrame;
import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.request.operationStatsRequest;
import com.onexshield.wmm.response.operationReponse;
import com.onexshield.wmm.mappers.operationRegisterMapper;
import com.onexshield.wmm.response.operationStatsResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class operationService {

    private final IOperationRepository iOperationRepository;
    private final IAccountRepository iAccountRepository;
    private final operationRegisterMapper operationRegisterMapper;
    public operationReponse createOperation(operationRequest operation){
        Optional<account> account1= Optional.ofNullable(iAccountRepository.findByAccountId(operation.getAccountId()));
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

    public List<operationReponse> getAllOperationsByAccount(Integer id){
        List<operation> operations = iOperationRepository.findAllByAccount_AccountId(id);
        return operations
                .stream()
                .map(operation -> new operationReponse(
                        operation.getOperationId(),
                        operation.getAmount(),
                        operation.getOperationType().toString(),
                        operation.getDescription(),
                        new SimpleDateFormat("yyyy-dd-MM").format(operation.getTransactionDate()),
                        operation.getAccount().getAccountId()))
                .collect(Collectors.toList());
    }

    public operationReponse updateOperation(operationRequest operationRequest, Integer id) {
        operation operation = operationRegisterMapper.apply(operationRequest);
        operation.setOperationId(id);
        return operationRegisterMapper.operationToOperationResponse(iOperationRepository.save(operation));

    }

    public void deleteOperation(Integer id) {
        iOperationRepository.deleteById(id);
    }

    public operationReponse getOperation(Integer id) {
        return operationRegisterMapper.operationToOperationResponse(iOperationRepository.findById(id).orElseThrow());
    }

    public List<operationStatsResponse> getStats(operationStatsRequest request, Integer id) {
//        List<operation> allOperations= iOperationRepository.findAllByAccount_AccountId(id);
//        int sum = 0;
//        String rangeDate = new SimpleDateFormat("dd-MMM-YY").format(Date.parse(request.getStartDate().toString()));
//        Date incrementedDate = request.getStartDate();
//        List<operationStatsResponse> response = new ArrayList<>();
//        if(request.getColumnFrame().equals(columnFrame.DAY)){
//            for(operation op: allOperations){
//                if(request.getOperationType().equals(operationType.EXPENSE)){
//                    if(op.getOperationType().equals(operationType.EXPENSE) & op.getTransactionDate().after(incrementedDate)){
//                        sum += op.getAmount();
//                        Calendar c = Calendar.getInstance();
//                        c.setTime(incrementedDate);
//                        c.add(Calendar.DATE, 1);
//                        incrementedDate = c.getTime();
//                    }
//
//
//                }
//            }
//            operationStatsResponse os = new operationStatsResponse(rangeDate, sum);
//            response.add(os);
//
//        }

        return null;
    }
}
