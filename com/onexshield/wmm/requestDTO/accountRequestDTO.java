package com.onexshield.wmm.requestDTO;

import java.util.Date;
import java.util.List;

public record accountRequestDTO(
         String firstName,
         String lastName,
         String email,
         String password,
         String phoneNumber,
         String currency,
         String addressLabel,
         String country,
         String city,
         Date birthDate,
         List<securityAnswerRequestDTO> securityAnswers
) {
}
