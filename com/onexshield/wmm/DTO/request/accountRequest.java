package com.onexshield.wmm.DTO.request;

import java.util.Date;
import java.util.List;

public record accountRequest(
         String firstName,
         String lastName,
         String gender,
         String email,
         String password,
         String phoneNumber,
         String currency,
         String addressLabel,
         String country,
         String city,
         Date birthDate,
         List<securityAnswerRequest> securityAnswers
) {
}
