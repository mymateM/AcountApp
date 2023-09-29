package com.connect.accountApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AccountAppApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AccountAppApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
