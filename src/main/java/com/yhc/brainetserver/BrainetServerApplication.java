package com.yhc.brainetserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BrainetServerApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		// There is no default instance of BCryptPasswordEncoder that can be injected in the UserController class.
		// Therefore we need to manually create one here.
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(BrainetServerApplication.class, args);
	}
}
