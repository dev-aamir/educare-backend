package com.educare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EducareApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EducareApplication.class, args);
	}

	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EducareApplication.class);
    }
}
