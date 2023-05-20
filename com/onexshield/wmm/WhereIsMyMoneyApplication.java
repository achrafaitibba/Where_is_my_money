package com.onexshield.wmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WhereIsMyMoneyApplication {
	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyMoneyApplication.class, args);
	}


	//todo user records/ classes for requests/responses ?
}
