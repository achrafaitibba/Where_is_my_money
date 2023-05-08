package com.onexshield.wmm.service;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.repository.IOperationRepository;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.DTOMapper.operationRegisterMapper;

import com.onexshield.wmm.requestDTO.accountRequestDTO;
import com.onexshield.wmm.requestDTO.operationRequestDTO;
import com.onexshield.wmm.responseDTO.accountResponseDTO;
import com.onexshield.wmm.responseDTO.operationReponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class operationService {
    @Autowired
    IOperationRepository iOperationRepository;
    @Autowired
    account account;
    @Autowired
    IAccountRepository iAccountRepository;
    @Autowired
    operationRegisterMapper operationRegisterMapper;
    public operationReponseDTO createOperation(operationRequestDTO operation){
        Optional<account> account1= Optional.ofNullable(iAccountRepository.findByAccountId(operation.accountId()));
        if(account1.isPresent()){
            return operationRegisterMapper.operationToOperationResponse(
                    iOperationRepository.save(
                            operationRegisterMapper.apply(operation)
                    )
            );
        }else {
            throw new IllegalArgumentException("Account doesn't exist");
        }

    }

    public List<operationReponseDTO> getAllOperationsByAccount(UUID id){
        List<operation> operations = iOperationRepository.getAllByAccount_AccountId(id);
        return operations
                .stream()
                .map(operation -> new operationReponseDTO(
                        operation.getOperationId(),
                        operation.getAmount(),
                        operation.getOperationType().toString(),
                        operation.getDescription(),
                        operation.getAccount().getAccountId()))
                .collect(Collectors.toList());
    }

    public operationReponseDTO updateOperation(operationRequestDTO operationRequestDTO, UUID id) {
//        public accountResponseDTO updateAccount(accountRequestDTO account, String email) {
//            UUID id = iAccountRepository.findByUser_Email(email).getAccountId();
//            account account1 = accountRegisterMapper.apply(account);
//            account1.setAccountId(id);
//            account1.getUser().setUser_id(id);
//            return accountRegisterMapper.accountToAccountReponseDTO(iAccountRepository.save(account1));
//        }
        operation operation = operationRegisterMapper.apply(operationRequestDTO);
        operation.setOperationId(id);

        return operationRegisterMapper.operationToOperationResponse(iOperationRepository.save(operation));

    }

    public void deleteOperation(UUID id) {
        iOperationRepository.deleteById(id);
    }
}
