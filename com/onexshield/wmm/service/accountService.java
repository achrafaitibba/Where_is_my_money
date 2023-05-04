package com.onexshield.wmm.service;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class accountService {
    @Autowired
    IAccountRepository iAccountRepository ;

    public account addAccount(account account){
        if(account.getSecurityAnswers().size() != 3)
            throw new IllegalArgumentException("The user should select 3 questions");
        else
            return iAccountRepository.save(account);
    }



}
