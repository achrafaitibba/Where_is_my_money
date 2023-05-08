package com.onexshield.wmm.service;

import com.onexshield.wmm.modelMappers.accountRegisterMapper;
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
        /*
        UUID id = UUID.randomUUID();
        List<securityAnswer> securityAnswers = new ArrayList<>();
        address address = new address();
        address.setAddressLabel(accountDTO.getAddressLabel());
        address.setCountry(accountDTO.getCountry());
        address.setCity(accountDTO.getCity());
        account.setAccountId(id);
        account.setCurrency(currency.valueOf(accountDTO.getCurrency()));
        user.setUser_id(id);
        user.setAddress(address);
        user.setEmail(accountDTO.getEmail());
        user.setPassword(accountDTO.getPassword());
        user.setFirstName(accountDTO.getFirstName());
        user.setLastName(accountDTO.getLastName());
        user.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setUser(user);
        for(int i = 0; i<accountDTO.getSecurityAnswers().size(); i++){
            var as = new securityAnswer();
            var qe = new securityQuestion();
            as.setAnswer(accountDTO.getSecurityAnswers().get(i).getAnswer());
            qe.setQuestionId(accountDTO.getSecurityAnswers().get(i).getQuestionId());
            as.setQuestion(qe);
            as.setAccount(account);
            securityAnswers.add(as);
        }
        account.setSecurityAnswers(securityAnswers);
        iAccountRepository.save(account);
        return new accountResponseDTO(id,
                account.getUser().getFirstName(),
                account.getUser().getLastName(),
                account.getUser().getEmail(),
                account.getUser().getPhoneNumber(),
                account.getCurrency().toString(),
                account.getUser().getAddress().getAddressLabel(),
                account.getUser().getAddress().getCountry(),
                account.getUser().getAddress().getCity(),
                account.getUser().getBirthDate(),
                account.getCreationDate(),
                accountDTO.getSecurityAnswers()
                );
                */
        //        UUID id = UUID.randomUUID();
//        if(account.getSecurityAnswers().size() != 3){
//
//            throw new IllegalArgumentException("The user should select 3 questions");
//        }
//         else{
//            account.setAccountId(id);
//            account.getUser().setUser_id(id);
//            for (securityAnswer sa:account.getSecurityAnswers()
//                 ) {
//                sa.setAccount(account);
//            }
//
//
//            return iAccountRepository.save(account);
//        }

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
