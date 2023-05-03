package com.onexshield.wmm.service;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.repository.IOperationRepository;
import com.onexshield.wmm.repository.IAccountRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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

    public  String createOperation(operation operation){
        String msg = "nn hh";
        Optional<account> account1= Optional.ofNullable(iAccountRepository.findByAccountId(operation.getAccountId()));
        if(account1.isPresent()){
            msg = "Done";
            iOperationRepository.save(operation);
        }
        return msg;
    }

    public List<operation> getAllOperations(Integer id){
        return iOperationRepository.getAllByAccountId(id);
    }
}
