package com.eldar.config_server;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigServer

public class ConfigServerApplication implements CommandLineRunner {

	@Value("${spring.cloud.config.server.git.username}")
	private String getUsername;
	@Value("${spring.cloud.config.server.git.password}")
	private String getPassword;


	public static void main(String[] args) {

		SpringApplication.run(ConfigServerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("GitUsername: " + getUsername);
		System.out.println("GitPassword: " + getPassword);
	}
}
