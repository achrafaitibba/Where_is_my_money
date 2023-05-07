package com.onexshield.wmm.service;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.repository.IOperationRepository;
import com.onexshield.wmm.repository.IAccountRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class operationService {
    @Autowired
    IOperationRepository iOperationRepository;
    @Autowired
    account account;
    @Autowired
    IAccountRepository iAccountRepository;

    public  operation createOperation(operation operation){
        Optional<account> account1= Optional.ofNullable(iAccountRepository.findByAccountId(operation.getAccount().getAccountId()));
        if(account1.isPresent()){
            iOperationRepository.save(operation);
            return operation;
        }else {
            throw new IllegalArgumentException("Account doesn't exist");
        }

    }

    public List<operation> getAllOperationsByAccount(Integer id){
        return iOperationRepository.getAllByAccount_AccountId(id);
    }
}
