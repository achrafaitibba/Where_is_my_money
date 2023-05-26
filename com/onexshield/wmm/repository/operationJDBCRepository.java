package com.onexshield.wmm.repository;

import com.onexshield.wmm.mappers.operationStatsMapper;
import com.onexshield.wmm.request.columnFrameRequest;
import com.onexshield.wmm.request.operationStatsRequest;
import com.onexshield.wmm.response.operationStatsResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.onexshield.wmm.model.operationType.EXPENSE;
import static com.onexshield.wmm.request.columnFrameRequest.DAY;

@Repository
@Data
@RequiredArgsConstructor
public class operationJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    public String queryBuilder(Integer id, operationStatsRequest request){
        String querySelection = "";
        String queryGroupBy = "";
        switch (request.getColumnFrame()){
            case DAY -> {
                querySelection = "sum(amount), transaction_date";
                queryGroupBy = "transaction_date";
            }
            case WEEK -> {
                querySelection = "sum(amount), YEAR(transaction_date), MONTH(transaction_date), WEEK(transaction_date)";
                queryGroupBy = "YEAR(transaction_date), MONTH(transaction_date), WEEK(transaction_date)";

            }
            case MONTH -> {
                querySelection = "sum(amount), YEAR(transaction_date), MONTH(transaction_date)";
                queryGroupBy = "YEAR(transaction_date), MONTH(transaction_date)";

            }
            case YEAR -> {
                querySelection = "sum(amount), YEAR(transaction_date)";
                queryGroupBy = "YEAR(transaction_date)";
            }

        }
        return          "SELECT "+querySelection+" from operation where account_account_id = "+id+" " +
                        "and operation_type = '"+request.getOperationType()+"' " +
                        "and transaction_date >= '"+request.getStartDate()+"' " +
                        "group by "+queryGroupBy+"";
    }
    public List<operationStatsResponse> getStats1(Integer id, operationStatsRequest request)throws Exception {
        return jdbcTemplate.query(queryBuilder(id, request), new operationStatsMapper());
    }


//day         new operationStatsResponse(resultSet.getDouble(1),resultSet.getString(2));
//week        new operationStatsResponse(resultSet.getDouble(1),resultSet.getString(2)+"-"+resultSet.getString(3)+"-Week:"+resultSet.getString(4)));
//month       new operationStatsResponse(resultSet.getDouble(1),resultSet.getString(2)+"-"+resultSet.getString(3)));
//year        new operationStatsResponse(resultSet.getDouble(1),resultSet.getString(2));


}
