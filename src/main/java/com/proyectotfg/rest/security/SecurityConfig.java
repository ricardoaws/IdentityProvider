package com.proyectotfg.rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

// Indicamos esta anotación para definir que esta clase es una clase de configuracion
@Configuration
// Anotación que nos sirve para habilitar Spring Security
@EnableWebSecurity
// La otorgamos al Bean una preferencia del orden 1
@Order(1)
// Utilizamos esta notación para realizar la injección por constructor
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	// Exponemos el mecanismo de autenticación como un Bean y le damos el nombre AUTHENTICATION_MANAGER para que no haya
	// posibilidad de equivocación al exponerlo
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	// Este método es el que nos permite configurar la autenticación
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Indicamos que los usuarios para la autenticacion se obtendran con el servicio userDetailsService
		// La password será cifrada con Bcrypt
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	
    // Este método nos permite configurar el control de acceso
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable()
				//Permitimos que se realicen peticiones a los endpoints "/login", "/oauth/authorize"
				.requestMatchers()
				.antMatchers("/login", "/oauth/authorize")
				.and()
				// Requerimos que para estas peticiones los usuarios estén autenticados
				.authorizeRequests()
				// Sirve para habilitar la consola de H2
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()
				.antMatchers("/oauth/check_token").permitAll()
				.anyRequest().authenticated()
				.and()
				// Permitimos el formulario de login a cualquier usuario
				.formLogin().permitAll();

		// Sirve para habilitar la consola de H2
		http.headers().frameOptions().disable();
		
		
	}
	
}
