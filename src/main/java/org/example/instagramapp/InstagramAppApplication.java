package org.example.instagramapp;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
@RequiredArgsConstructor

public class InstagramAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramAppApplication.class, args);

	}

	@Bean
	public JavaMailSender get() {
		return new JavaMailSenderImpl();
	}
}