package com.proyectotfg.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Indicamos esta anotación para definir que esta clase es una clase de configuracion
@Configuration
public class PasswordEncoderConfig {

	// Indicamos esta anotación para injectar el bean PasswordEndorer de Spring Security
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
