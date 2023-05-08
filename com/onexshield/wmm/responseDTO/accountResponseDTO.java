package com.onexshield.wmm.responseDTO;


import com.onexshield.wmm.requestDTO.securityAnswerRequestDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public record accountResponseDTO (
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
        List<securityAnswerRequestDTO> securityAnswers
) {

}
