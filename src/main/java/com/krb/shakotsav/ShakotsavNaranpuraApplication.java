package com.krb.shakotsav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


@SpringBootApplication
public class ShakotsavNaranpuraApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ShakotsavNaranpuraApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ShakotsavNaranpuraApplication.class, args);
	}
}
