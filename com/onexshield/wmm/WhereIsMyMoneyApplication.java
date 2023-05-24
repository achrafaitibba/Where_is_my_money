package com.onexshield.wmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class WhereIsMyMoneyApplication {
	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyMoneyApplication.class, args);
	}

	//todo create some objects to avoid creating objects everytime i try to test
}
