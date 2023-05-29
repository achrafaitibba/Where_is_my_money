package com.onexshield.wmm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class securityAnswerRequest {
    private Long answerId;
    private String answer;
    private Integer questionId;
}
