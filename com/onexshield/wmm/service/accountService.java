package com.onexshield.wmm.service;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.repository.IAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class accountService {
    @Autowired
    IAccountRepository iAccountRepository ;

    public account addAccount(account account){
        if(account.getSecurityAnswers().size() != 3)
            throw new IllegalArgumentException("The user should select 3 questions");
         else
            return iAccountRepository.save(account);
    }

    public account findByEmail(String email) {
        return iAccountRepository.findByUser_Email(email);
    }


}
