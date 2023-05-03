package com.onexshield.wmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WhereIsMyMoneyV2Application {

	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyMoneyV2Application.class, args);
	}

}
