package com.onexshield.wmm.request;

import com.onexshield.wmm.model.currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class accountInfoRequest {
    private String email;
    private currency currency;
}
