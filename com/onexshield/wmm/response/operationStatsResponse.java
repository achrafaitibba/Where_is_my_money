package com.onexshield.wmm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class operationStatsResponse {
    private String rangeDate;
    private int sum;
}
