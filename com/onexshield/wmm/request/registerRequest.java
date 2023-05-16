package com.onexshield.wmm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class registerRequest {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private String addressLabel;
    private String country;
    private String city;
    private String email;
    private String password;
    private String currency;
    private List<securityAnswerRequest> securityAnswers;

}
