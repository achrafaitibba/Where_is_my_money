package com.onexshield.wmm.request;

import com.onexshield.wmm.model.currency;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class accountInfoRequest {
    private String email;
    private currency currency;
}
