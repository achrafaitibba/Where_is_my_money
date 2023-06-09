package com.onexshield.wmm.request;

import com.onexshield.wmm.model.gender;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class userInfoRequest {
    private String firstName;
    private String lastName;
    private gender gender;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String phoneNumber;
    private String addressLabel;
    private String country;
    private String city;

}
