package com.onexshield.wmm.request;

import com.onexshield.wmm.model.gender;
import com.onexshield.wmm.model.currency;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
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
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private gender gender;
    private String phoneNumber;
    private String addressLabel;
    private String country;
    private String city;
    private String email;
    private String password;
    private currency currency;
    @Size(min = 3, max = 3, message = "3 security questions are required")
    private List<securityAnswerRegisterRequest> securityAnswers;

}
