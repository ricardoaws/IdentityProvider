package com.proyectotfg.rest;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyectotfg.rest.upload.StorageService;

@SpringBootApplication
//Habilitamos el mecanismo de auditoria para que funcione la notación @EntityListeners(AuditingEntityListener.class) de la clase UserEntity
@EnableJpaAuditing
// Esta es la clase principal de Spring Boot que levanta el servidor de aplicación
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner init(StorageService storageService, PasswordEncoder passwordEncoder) {
		return args -> {
			// Inicializamos el servicio de ficheros
			storageService.deleteAll();
			storageService.init();
		};

	}

}
