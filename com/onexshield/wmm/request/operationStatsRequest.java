package com.onexshield.wmm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class operationStatsRequest {
    private String startDate;
    private columnFrameRequest columnFrame;
    private String operationType;
}
