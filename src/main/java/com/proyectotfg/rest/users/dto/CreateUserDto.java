package com.proyectotfg.rest.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Creamos esta clase para tener un DTO que utilizaremos para crear un usuario. Con esto conseguimos utilizar los atributos que
 * consideramos necesarios para crear una entidad UserEntity,sin necesidad de tener que utilizar todos los atributos de la entidad
 * o añadiendo el introducir 2 veces la password para verificar que se está indicando de forma correcta
 */

// Añadimos los métodos Get y Set por anotacion
@Getter @Setter
// Añadimos el contructor sin argumentos, el constructor con todos los argumentos y la creación de un builder
@NoArgsConstructor @AllArgsConstructor @Builder
public class CreateUserDto {
	
	private String username;
	private String fullname;
	private String email;
	private String password;
	private String password2;

}
