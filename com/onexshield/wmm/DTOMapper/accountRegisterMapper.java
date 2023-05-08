package com.onexshield.wmm.DTOMapper;

import com.onexshield.wmm.model.*;
import com.onexshield.wmm.requestDTO.accountRequestDTO;
import com.onexshield.wmm.requestDTO.securityAnswerRequestDTO;
import com.onexshield.wmm.responseDTO.accountResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
@Service
public class accountRegisterMapper implements Function<accountRequestDTO, account> {
    @Autowired
    account account;

    public account apply(accountRequestDTO accountRequestDTO){
        UUID id = UUID.randomUUID();
        List<securityAnswer> securityAnswers = new ArrayList<>();
        address address = new address();
        user user = new user();
        for(int i = 0; i<accountRequestDTO.securityAnswers().size(); i++){
            var as = new securityAnswer();
            var qe = new securityQuestion();
            as.setAnswer(accountRequestDTO.securityAnswers().get(i).answer());
            as.setAnswerId(accountRequestDTO.securityAnswers().get(i).answerId());
            qe.setQuestionId(accountRequestDTO.securityAnswers().get(i).questionId());
            as.setQuestion(qe);
            as.setAccount(account);
            securityAnswers.add(as);
        }
        address.setAddressLabel(accountRequestDTO.addressLabel());
        address.setCountry(accountRequestDTO.country());
        address.setCity(accountRequestDTO.city());
        user.setUser_id(id);
        user.setFirstName(accountRequestDTO.firstName());
        user.setLastName(accountRequestDTO.lastName());
        user.setEmail(accountRequestDTO.email());
        user.setPassword(accountRequestDTO.password());
        user.setAddress(address);
        user.setPhoneNumber(accountRequestDTO.phoneNumber());
        account.setAccountId(id);
        account.setCurrency(currency.valueOf(accountRequestDTO.currency()));
        account.setUser(user);
        account.setSecurityAnswers(securityAnswers);
        return  account;
    }

    public accountResponseDTO accountToAccountReponseDTO(account account){
        return new accountResponseDTO(
                account.getAccountId(),
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
                account.getSecurityAnswers()
                        .stream()
                        .map(
                                securityAnswer -> new securityAnswerRequestDTO(
                                        securityAnswer.getAnswerId(),
                                        securityAnswer.getAnswer(),
                                        securityAnswer.getQuestion().getQuestionId())
                        )
                        .toList()

        );
    }
}
