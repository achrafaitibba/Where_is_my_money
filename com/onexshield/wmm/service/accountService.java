package com.onexshield.wmm.service;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.securityAnswer;
import com.onexshield.wmm.repository.IAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
public class accountService {
    @Autowired
    IAccountRepository iAccountRepository ;

    public account addAccount(account account){
        UUID id = UUID.randomUUID();

        if(account.getSecurityAnswers().size() != 3){

            throw new IllegalArgumentException("The user should select 3 questions");
        }
         else{
            account.setAccountId(id);
            account.getUser().setUser_id(id);
            for (securityAnswer sa:account.getSecurityAnswers()
                 ) {
                sa.setAccount(account);
            }


            return iAccountRepository.save(account);
        }

    }

    public account findByEmail(String email) {
        return iAccountRepository.findByUser_Email(email);
    }

    public account updateAccount(account account, String email) {
        UUID id = iAccountRepository.findByUser_Email(email).getAccountId();
        account.setAccountId(id);
        account.getUser().setUser_id(id);
        iAccountRepository.save(account);

        return account;

    }
    public void deleteAccount(String email) {
        iAccountRepository.setInactive(email);
    }


}
