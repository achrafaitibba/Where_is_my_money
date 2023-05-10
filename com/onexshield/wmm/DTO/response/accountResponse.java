package com.onexshield.wmm.DTO.response;


import com.onexshield.wmm.DTO.request.securityAnswerRequest;

import java.util.List;
import java.util.UUID;


public record accountResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String currency,
        String addressLabel,
        String country,
        String city,
        String birthDate,
        String creationDate,
        List<securityAnswerRequest> securityAnswers
) {

}
