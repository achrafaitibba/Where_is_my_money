package com.onexshield.wmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WhereIsMyMoneyApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyMoneyApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WhereIsMyMoneyApplication.class);
	}

	//todo create some objects to avoid creating objects everytime i try to test
	//todo /testing and mocking
	//todo /delete operation by Id, check its return, do what if Id doesn't exist
	//todo /add pro env to documentation ; https://whereismymoney-production-9d7f.up.railway.app/
}
