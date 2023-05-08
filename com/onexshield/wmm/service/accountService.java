package com.onexshield.wmm.service;

import com.onexshield.wmm.DTOMapper.accountRegisterMapper;
import com.onexshield.wmm.model.*;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.requestDTO.accountRequestDTO;
import com.onexshield.wmm.responseDTO.accountResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
public class accountService {
    @Autowired
    IAccountRepository iAccountRepository ;
    @Autowired
    account account;
    @Autowired
    user user;
    @Autowired
    accountRegisterMapper accountRegisterMapper;

    public accountResponseDTO addAccount(accountRequestDTO accountDTO){
        return accountRegisterMapper.accountToAccountReponseDTO(
                iAccountRepository.save(accountRegisterMapper.apply(accountDTO))
        );
    }

    public accountResponseDTO findByEmail(String email) {
        return accountRegisterMapper.accountToAccountReponseDTO(iAccountRepository.findByUser_Email(email));
    }

    public accountResponseDTO updateAccount(accountRequestDTO account, String email) {
        UUID id = iAccountRepository.findByUser_Email(email).getAccountId();
        account account1 = accountRegisterMapper.apply(account);
        account1.setAccountId(id);
        account1.getUser().setUser_id(id);
        return accountRegisterMapper.accountToAccountReponseDTO(iAccountRepository.save(account1));
        /*
        UUID id = iAccountRepository.findByUser_Email(email).getAccountId();
        account.setAccountId(id);
        account.getUser().setUser_id(id);
        iAccountRepository.save(account);

        return account;
        */

    }
    public void deleteAccount(String email) {
        iAccountRepository.setInactive(email);
    }


}
