package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.*"})
public class Demo22Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo22Application.class, args);

//		SpringApplication application = new SpringApplication(Demo22Application.class);
//		application.setWebApplicationType(WebApplicationType.SERVLET);
//		application.setWebApplicationType(WebApplicationType.NONE);
//		application.run(args);

	}

}
