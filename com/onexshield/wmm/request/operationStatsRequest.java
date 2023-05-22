package com.onexshield.wmm.request;

import com.onexshield.wmm.model.operationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class operationStatsRequest {
    private Date startDate;
    private columnFrame columnFrame;
    private operationType operationType;
}
