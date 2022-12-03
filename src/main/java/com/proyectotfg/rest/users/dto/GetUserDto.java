package com.proyectotfg.rest.users.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Creamos esta clase para tener un DTO que utilizaremos para obtener un usuario. Con esto conseguimos utilizar los atributos que
 * consideramos necesarios para obtener una entidad UserEntity, sin necesidad de tener que utilizar todos los atributos de la entidad.
 * Los atributos definidos, serán la información que le devolveremos al usuario al crear un nuevo usuario
 */

// Añadimos los métodos Get y Set por anotacion
@Getter @Setter
// Añadimos el contructor sin argumentos, el constructor con todos los argumentos y la creación de un builder
@NoArgsConstructor @AllArgsConstructor @Builder
public class GetUserDto {
	
	private String username;
	private String fullName;
	private String email;
	private Set<String> roles;
	
	

}
