package com.proyectotfg.rest.users.dto;

import com.proyectotfg.rest.users.model.UserEntity;
import com.proyectotfg.rest.users.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Esta clase es un converter de UserEntity a GetUserDto
 */

// Indicamos que esta clase se trata de un componente. Por ejemplo, para entender mejor que es un componente,
// @Repository y @Service son especializaciones de un componente
@Component
public class UserDtoConverter {

	//Recibimos un UserEntity y devolvemos un GetUserDto
	public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
		return GetUserDto.builder()
				.username(user.getUsername())
				.fullName(user.getFullName())
				.email(user.getEmail())
				// Obtenemos los roles del usuario y mediante uin stream los mapeamos a través del método name de la enumeracion UserRole
				// y lo guardamos en un Set. Esto lo necesitamos ya que el usuario puede tener varios roles.
				.roles(user.getRoles().stream()
							.map(UserRole::name)
							.collect(Collectors.toSet())
				).build();
	}

}
