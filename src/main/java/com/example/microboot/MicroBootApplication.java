package com.example.microboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MicroBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroBootApplication.class, args);
	}

}
