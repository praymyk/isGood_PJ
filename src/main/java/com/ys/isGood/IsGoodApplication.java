package com.ys.isGood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class IsGoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsGoodApplication.class, args);
	}

}
