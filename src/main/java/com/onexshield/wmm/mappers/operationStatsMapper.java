package com.onexshield.wmm.mappers;

import org.springframework.jdbc.core.RowMapper;
import com.onexshield.wmm.response.operationStatsResponse;

import java.sql.ResultSet;
import java.sql.SQLException;


public class operationStatsMapper implements RowMapper<operationStatsResponse> {

    @Override
    public operationStatsResponse mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        if(columnCount == 2){ //Day or Year case
            return new operationStatsResponse(
                    resultSet.getDouble(1),
                    resultSet.getString(2)
            );
        } else if (columnCount == 3) { //Month case
            return new operationStatsResponse(
                    resultSet.getDouble(1),
                    resultSet.getString(2)+"-"+resultSet.getString(3));
        }
        else{ //Week case
            return new operationStatsResponse(resultSet.getDouble(1),
                    resultSet.getString(2)+"-"+resultSet.getString(3)+"-Week:"+resultSet.getString(4));
        }
    }

}
