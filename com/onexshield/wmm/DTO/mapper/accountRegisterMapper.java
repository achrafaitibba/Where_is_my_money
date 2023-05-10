package com.onexshield.wmm.DTO.mapper;

import com.onexshield.wmm.model.*;
import com.onexshield.wmm.DTO.request.accountRequest;
import com.onexshield.wmm.DTO.request.securityAnswerRequest;
import com.onexshield.wmm.DTO.response.accountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
@Service
public class accountRegisterMapper implements Function<accountRequest, account> {
    @Autowired
    account account;

    public account apply(accountRequest accountRequest){
        UUID id = UUID.randomUUID();
        List<securityAnswer> securityAnswers = new ArrayList<>();
        address address = new address();
        user user = new user();
        for(int i = 0; i< accountRequest.securityAnswers().size(); i++){
            var as = new securityAnswer();
            var qe = new securityQuestion();
            as.setAnswer(accountRequest.securityAnswers().get(i).answer());
            as.setAnswerId(accountRequest.securityAnswers().get(i).answerId());
            qe.setQuestionId(accountRequest.securityAnswers().get(i).questionId());
            as.setQuestion(qe);
            as.setAccount(account);
            securityAnswers.add(as);
        }
        address.setAddressLabel(accountRequest.addressLabel());
        address.setCountry(accountRequest.country());
        address.setCity(accountRequest.city());
        user.setUser_id(id);
        user.setFirstName(accountRequest.firstName());
        user.setLastName(accountRequest.lastName());
        user.setEmail(accountRequest.email());
        user.setPassword(accountRequest.password());
        user.setAddress(address);
        user.setPhoneNumber(accountRequest.phoneNumber());
        user.setBirthDate(accountRequest.birthDate());
        account.setAccountId(id);
        account.setCurrency(currency.valueOf(accountRequest.currency()));
        account.setUser(user);
        account.setSecurityAnswers(securityAnswers);
        return  account;
    }

    public accountResponse accountToAccountReponseDTO(account account){
        return new accountResponse(
                account.getAccountId(),
                account.getUser().getFirstName(),
                account.getUser().getLastName(),
                account.getUser().getEmail(),
                account.getUser().getPhoneNumber(),
                account.getCurrency().toString(),
                account.getUser().getAddress().getAddressLabel(),
                account.getUser().getAddress().getCountry(),
                account.getUser().getAddress().getCity(),
                new SimpleDateFormat("yyyy-dd-MM").format(account.getUser().getBirthDate()),
                new SimpleDateFormat("yyyy-dd-MM").format(account.getCreationDate()),
                account.getSecurityAnswers()
                        .stream()
                        .map(
                                securityAnswer -> new securityAnswerRequest(
                                        securityAnswer.getAnswerId(),
                                        securityAnswer.getAnswer(),
                                        securityAnswer.getQuestion().getQuestionId())
                        )
                        .toList()

        );
    }
}
