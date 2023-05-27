package com.onexshield.wmm.response;

import com.onexshield.wmm.model.gender;
import com.onexshield.wmm.model.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class accountResponse {
    private UUID accountId;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private gender gender;
    private String phoneNumber;
    private String addressLabel;
    private String country;
    private String city;
    private String email;
    private currency currency;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    private List<securityAnswerResponse> securityAnswers;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

}
