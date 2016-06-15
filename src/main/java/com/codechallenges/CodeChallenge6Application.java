package com.codechallenges;

import com.codechallenges.repository.PresentJpaRepository;
import com.codechallenges.repository.WishItemJpaRepository;
import com.codechallenges.service.PresentServiceImpl;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "com.codechallenges.repository")
@PropertySource({"classpath:application.properties"})
public class CodeChallenge6Application {

	public static void main(String[] args) {
		SpringApplication.run(CodeChallenge6Application.class, args);
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	@Bean
	public PresentServiceImpl presentService1(PresentJpaRepository presentJpaRepository, WishItemJpaRepository wishItemJpaRepository){
		return new PresentServiceImpl(presentJpaRepository, wishItemJpaRepository);
	}

}
