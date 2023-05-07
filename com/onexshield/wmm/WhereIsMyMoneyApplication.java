package com.onexshield.wmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WhereIsMyMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyMoneyApplication.class, args);
	}

}
