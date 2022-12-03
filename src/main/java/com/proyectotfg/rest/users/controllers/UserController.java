package com.proyectotfg.rest.users.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectotfg.rest.users.dto.CreateUserDto;
import com.proyectotfg.rest.users.dto.GetUserDto;
import com.proyectotfg.rest.users.dto.UserDtoConverter;
import com.proyectotfg.rest.users.services.UserEntityService;

import lombok.RequiredArgsConstructor;

// Con esta anotación definimos que esta clase se trata de un controlador de tipo Rest
@RestController
// Todas las peticiones en este controlador se mapean al path /user
@RequestMapping("/user")
// Con esta anotacion se realiza la inyección por constructor
@RequiredArgsConstructor
public class UserController { 
	
	private final UserEntityService userEntityService;
	private final UserDtoConverter userDtoConverter;
	
	// La peticion Post se realiza a la raiz de /user
	// En el Body del Post pasaremos un DTO del tipo CreateUserDto que será el nuevo usuario que estamos creando
	// Como se puede observar el controlador utiliza el servicio userEntityService para crear el usuario en el repositorio
	@PostMapping("/")
	public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newUser) {
			return ResponseEntity.status(HttpStatus.CREATED).body(
					userDtoConverter.convertUserEntityToGetUserDto(
							userEntityService.nuevoUsuario(newUser)));

	}

}
