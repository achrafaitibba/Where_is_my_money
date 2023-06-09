package com.onexshield.wmm.mappers;


import com.onexshield.wmm.model.*;
import com.onexshield.wmm.request.registerRequest;
import com.onexshield.wmm.response.accountResponse;
import com.onexshield.wmm.response.securityAnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Component
public class accountMapper {
    private final account account_;
    private final PasswordEncoder passwordEncoder;

    public account requestToAccount(registerRequest request){
        Long id = Math.abs(new Random().nextLong());
        account_.setAccountId(id);
        List<securityAnswer> securityAnswers = new ArrayList<>();
        for(int i = 0; i< request.getSecurityAnswers().size(); i++){
            var as = new securityAnswer();
            var qe = new securityQuestion();
            as.setAnswer(request.getSecurityAnswers().get(i).getAnswer());
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
                        .gender(request.getGender())
                        .phoneNumber(request.getPhoneNumber())
                        .address(address.builder()
                                .addressLabel(request.getAddressLabel())
                                .country(request.getCountry())
                                .city(request.getCity())
                                .build())
                        .build())
                .creationDate(new Date())
                .currency(request.getCurrency())
                .accountStatus(status.ACTIVE)
                .securityAnswers(securityAnswers)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role.USER)
                .build();

    }


    public accountResponse accountToResponse(account account,
                                           String accessToken,
                                           String refreshToken){
        return accountResponse.builder()
                .accountId(String.valueOf(account.getAccountId()))
                .firstName(account.getPerson().getFirstName())
                .lastName(account.getPerson().getLastName())
                .birthDate(new SimpleDateFormat("YYYY-MM-dd").format(account.getPerson().getBirthDate()))
                .gender(account.getPerson().getGender())
                .phoneNumber(account.getPerson().getPhoneNumber())
                .addressLabel(account.getPerson().getAddress().getAddressLabel())
                .country(account.getPerson().getAddress().getCountry())
                .city(account.getPerson().getAddress().getCity())
                .email(account.getEmail())
                .currency(account.getCurrency())
                .creationDate(account.getCreationDate())
                .profileImageId(account.getProfileImage() == null ? null : String.valueOf(account.getProfileImage().getImageId()))
                .securityAnswers(account.getSecurityAnswers()
                        .stream()
                        .map(
                                securityAnswer -> new securityAnswerResponse(
                                        String.valueOf(securityAnswer.getAnswerId()),
                                        securityAnswer.getAnswer(),
                                        securityAnswer.getQuestion().getQuestion(),
                                        securityAnswer.getQuestion().getQuestionId()
                                )
                        ).toList()
                )
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
