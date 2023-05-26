package com.onexshield.wmm.dataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
@Component
public class connection {
    @Value("${app.database.driver-class-name}")
    private String driver;
    @Value("${app.database.url}")
    private String url;

    @Value("${app.database.username}")
    private String user;

    @Value("${app.database.password}")
    private String password;

    public Connection getConnection()throws Exception{
        //todo /use JDBC TEMPLATE
        Class.forName(driver);
        return DriverManager.getConnection(
                url,
                user,
                password);
    }

}
