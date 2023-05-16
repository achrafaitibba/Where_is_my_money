package com.onexshield.wmm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class securityAnswerResponse {
    private Integer answerId;
    private String answer;
    private String question;
    private Integer questionId;
}
