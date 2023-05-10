package com.onexshield.wmm.service;

import com.onexshield.wmm.DTO.mapper.accountRegisterMapper;
import com.onexshield.wmm.model.*;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.repository.IUserRepository;
import com.onexshield.wmm.DTO.request.accountRequest;
import com.onexshield.wmm.DTO.response.accountResponse;
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
    @Autowired
    IUserRepository iUserRepository;

    public accountResponse addAccount(accountRequest accountDTO){
        return accountRegisterMapper.accountToAccountReponseDTO(
                iAccountRepository.save(accountRegisterMapper.apply(accountDTO))
        );
    }

    public accountResponse findByEmail(String email) {
        return accountRegisterMapper.accountToAccountReponseDTO(iAccountRepository.findByUser_Email(email));
    }

    public accountResponse updateAccount(accountRequest account, String email) {
        UUID id = iAccountRepository.findByUser_Email(email).getAccountId();
        account account1 = accountRegisterMapper.apply(account);
        account1.setAccountId(id);
        account1.getUser().setUser_id(id);
        return accountRegisterMapper.accountToAccountReponseDTO(iAccountRepository.save(account1));
    }
    public void deleteAccount(String email) {
        iAccountRepository.setInactive(email);
    }


    public int updatePassword(UUID id, String oldPassword, String newPassword) {
        return iUserRepository.updatePassword(id, oldPassword, newPassword);
    }
}
