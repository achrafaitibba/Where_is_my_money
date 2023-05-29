package com.onexshield.wmm.repository;

import com.onexshield.wmm.mappers.operationStatsMapper;
import com.onexshield.wmm.request.operationStatsRequest;
import com.onexshield.wmm.response.operationStatsResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Data
@RequiredArgsConstructor
public class operationRepositoryJDBC {

    private final JdbcTemplate jdbcTemplate;

    public String queryBuilder(Long id, operationStatsRequest request){
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
    public List<operationStatsResponse> getStats(Long id, operationStatsRequest request)throws Exception {
        return jdbcTemplate.query(queryBuilder(id, request), new operationStatsMapper());
    }




}
