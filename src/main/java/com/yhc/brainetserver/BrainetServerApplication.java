package com.yhc.brainetserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yhc.brainetserver.security.BraiNetAuthenticationProvider;

@SpringBootApplication
public class BrainetServerApplication {

	@Bean
	public BraiNetAuthenticationProvider braiNetAuthenticationProvider() {
		// There is no default instance of BraiNetAuthenticationProvider that can be injected in other classes.
		// Therefore we need to manually create one here.
		return new BraiNetAuthenticationProvider();
	}

	public static void main(String[] args) {
		SpringApplication.run(BrainetServerApplication.class, args);
	}
}
