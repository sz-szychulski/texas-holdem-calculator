package com.thesis.texasholdemapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TexasHoldemAppApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TexasHoldemAppApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TexasHoldemAppApplication.class, args);
	}

}
