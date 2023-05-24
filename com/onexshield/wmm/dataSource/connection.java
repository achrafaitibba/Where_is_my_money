package com.onexshield.wmm.dataSource;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
@Component
public class connection {
    public Connection getConnection()throws Exception{ //todo /use spring @value
        Properties properties=new Properties();
        try (InputStream is = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(is);
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                properties.getProperty("dataBase.url"),
                properties.getProperty("dataBase.user"),
                properties.getProperty("dataBase.password"));
    }

}
