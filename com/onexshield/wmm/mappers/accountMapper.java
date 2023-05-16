package com.onexshield.wmm.mappers;


import com.onexshield.wmm.model.*;
import com.onexshield.wmm.request.registerRequest;
import com.onexshield.wmm.response.accountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class accountMapper {
    private final account account_;
    private final PasswordEncoder passwordEncoder;

    public account requestToAccount(registerRequest request){
        Integer id = new Random().nextInt();
        account_.setAccountId(id);
        List<securityAnswer> securityAnswers = new ArrayList<>();
        for(int i = 0; i< request.getSecurityAnswers().size(); i++){
            var as = new securityAnswer();
            var qe = new securityQuestion();
            as.setAnswer(request.getSecurityAnswers().get(i).getAnswer());
            as.setAnswerId(request.getSecurityAnswers().get(i).getAnswerId());
            qe.setQuestionId(request.getSecurityAnswers().get(i).getQuestionId());
            as.setQuestion(qe);
            as.setAccount(account_);
            securityAnswers.add(as);
        }
        return  account_.builder()
                .accountId(id)
                .person(person.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .birthDate(request.getBirthDate())
                        .gender(gender.valueOf(request.getGender()))
                        .phoneNumber(request.getPhoneNumber())
                        .address(address.builder()
                                .addressLabel(request.getAddressLabel())
                                .country(request.getCountry())
                                .city(request.getCity())
                                .build())
                        .build())
                .creationDate(new Date())
                .currency(currency.valueOf(request.getCurrency()))
                .accountStatus(status.ACTIVE)
                .securityAnswers(securityAnswers)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role.USER)
                .build();

    }


    public accountResponse accountResponse(account account){
        return accountResponse.builder()



                .build();
    }
}
