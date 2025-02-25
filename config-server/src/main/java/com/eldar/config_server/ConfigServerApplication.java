package com.eldar.config_server;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	@Value("${spring.cloud.config.server.git.username}")
	private static String gitUsername;

	@Value("${spring.cloud.config.server.git.password}")
	private static String gitPassword;

	public static void main(String[] args) {
		System.out.println("Git Username:"+ gitUsername);
		System.out.println("Git Password:"+ gitUsername);
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
