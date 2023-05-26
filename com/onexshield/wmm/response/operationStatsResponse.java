package com.onexshield.wmm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class operationStatsResponse {
    private double sum;
    private String date;

}
