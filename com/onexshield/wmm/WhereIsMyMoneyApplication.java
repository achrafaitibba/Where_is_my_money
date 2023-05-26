package com.onexshield.wmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class WhereIsMyMoneyApplication {
	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyMoneyApplication.class, args);
	}

	//todo create some objects to avoid creating objects everytime i try to test
	//todo /change schemes in api documentation or hide them ?
	//todo /remove create/drop, to have permanent data in database
	//todo /add dependencies and full project to github not only the src file
	//todo /check properties of accountService and all other classes to remover bad importation as below
	//    private final com.onexshield.wmm.authentication_configuration.token.jwtService jwtService

}
