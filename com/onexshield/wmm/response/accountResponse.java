package com.onexshield.wmm.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class accountResponse {
    private Integer accountId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private String addressLabel;
    private String country;
    private String city;
    private String email;
    private String currency;
    private Date creationDate;
    private List<securityAnswerResponse> securityAnswers;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

}
