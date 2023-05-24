package com.onexshield.wmm.repository;

import com.onexshield.wmm.request.columnFrameRequest;
import com.onexshield.wmm.request.operationStatsRequest;
import com.onexshield.wmm.response.operationStatsResponse;
import com.onexshield.wmm.dataSource.connection;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Data
@RequiredArgsConstructor
public class operationJDBCRepository {


    // todo / try a better way, that's a very ugly code, but it works anyways
    public List<operationStatsResponse> getStats(Integer id, operationStatsRequest request)throws Exception{ // todo /date format to pass :"2020-03-25"
        String query = "";
        List<operationStatsResponse> responses = new ArrayList<>();
        Connection connection = new connection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet ;
        if(request.getColumnFrameRequest().equals(columnFrameRequest.DAY)){
            query = "select sum(amount),transaction_date from operation where " +
                    " account_account_id = "+id+" and operation_type = '"+request.getOperationType()+"' and transaction_date >= '"+request.getStartDate()+ "' " +
                    "group by transaction_date";
            resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                responses.add(new operationStatsResponse(
                        resultSet.getDouble(1),
                        resultSet.getString(2)
                ));
            }
        } else if (request.getColumnFrameRequest().equals(columnFrameRequest.WEEK)) {
            query = "select sum(amount),YEAR(transaction_date),MONTH(transaction_date),WEEK(transaction_date) from operation where " +
                    " account_account_id = "+id+" and operation_type = '"+request.getOperationType()+"' and transaction_date >= '"+request.getStartDate()+ "' " +
                    "group by YEAR(transaction_date),MONTH(transaction_date),WEEK(transaction_date)";

            resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                responses.add(new operationStatsResponse(
                        resultSet.getDouble(1),
                        resultSet.getString(2)+"-"+resultSet.getString(3)+"-Week:"+resultSet.getString(4)
                ));
            }
        }else if (request.getColumnFrameRequest().equals(columnFrameRequest.MONTH)) {
            query = "select sum(amount),YEAR(transaction_date),MONTH(transaction_date) from operation where " +
                    " account_account_id = "+id+" and operation_type = '"+request.getOperationType()+"' and transaction_date >= '"+request.getStartDate()+ "' " +
                    "group by YEAR(transaction_date),MONTH(transaction_date)";

            resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                responses.add(new operationStatsResponse(
                        resultSet.getDouble(1),
                        resultSet.getString(2)+"-"+resultSet.getString(3)
                ));
            }

        }else if (request.getColumnFrameRequest().equals(columnFrameRequest.YEAR)) {
            query = "select sum(amount),YEAR(transaction_date) from operation where " +
                    " account_account_id = "+id+" and operation_type = '"+request.getOperationType()+"' and transaction_date >= '"+request.getStartDate()+ "' " +
                    "group by YEAR(transaction_date)";

            resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                responses.add(new operationStatsResponse(
                        resultSet.getDouble(1),
                        resultSet.getString(2)
                ));
            }
        }



        statement.close();
        connection.close();
        return responses;
    }


}
