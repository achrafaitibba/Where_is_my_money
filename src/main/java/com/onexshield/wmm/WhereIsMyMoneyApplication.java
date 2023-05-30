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
	//todo /add dependencies
	//todo /testing and mocking
	//todo /in readMe add: dependencies, version, how to test, link to front-end....
	//todo /clean the project, check names, remove unused packages, separate dependencies with comments
	//todo /add cities and countries  endpoint ?
}
