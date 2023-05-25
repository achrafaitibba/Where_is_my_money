package com.onexshield.wmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class WhereIsMyMoneyApplication {
	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyMoneyApplication.class, args);
	}

	//todo create some objects to avoid creating objects everytime i try to test
	//todo convert input to upperCase to avoid error while comparing/inserting/updating
	//todo update dates to return only date, no time > operation,account...
	//todo use UUID
}
