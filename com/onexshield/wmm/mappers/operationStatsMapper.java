package com.onexshield.wmm.mappers;

import org.springframework.jdbc.core.RowMapper;
import com.onexshield.wmm.response.operationStatsResponse;

import java.sql.ResultSet;
import java.sql.SQLException;


public class operationStatsMapper implements RowMapper<operationStatsResponse> {

    @Override
    public operationStatsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new operationStatsResponse(
                rs.getDouble(1),
                rs.getString(2)
        );
    }

}
