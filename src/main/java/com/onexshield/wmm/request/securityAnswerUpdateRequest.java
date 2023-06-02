package com.onexshield.wmm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class securityAnswerUpdateRequest {
    private Long answerId;
    private String answer;
    private Integer questionId;
}
