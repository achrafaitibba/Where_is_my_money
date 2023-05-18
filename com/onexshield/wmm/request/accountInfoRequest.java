package com.onexshield.wmm.request;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class accountInfoRequest {
    private String email;
    private String currency;
}
