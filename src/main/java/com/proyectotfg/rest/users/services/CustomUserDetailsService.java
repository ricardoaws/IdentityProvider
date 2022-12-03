package com.proyectotfg.rest.users.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Es un servicio al cual le damos el nombre userDetailsServices
@Service("userDetailsService")
// Utilizamos esta notación ya que como va a utilizar otro servicio, "userEntityService" la aprovechamos para inyectar dicho servicio
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	// Inyectamos el servicio userEntityService
	private final UserEntityService userEntityService;
	
	// Utilizamos el método definido en la clase UserEntityService para obtener un UserEntity, ya que implementa a UserDetails
	// Como lo que devuelve es un Optional, lanzamos una excepcion de tipo username, si no encuenta el UserEntity
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userEntityService.findUserByUsername(username)
					.orElseThrow(()-> new UsernameNotFoundException(username + " no encontrado"));
	}

	// Utilizamos el método definido en la clase UserEntityService para obtener un UserEntity,pero en este caso lo obtendremos
	// en base al ID del usuario que vendrá incluido en el JWT
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		return userEntityService.findById(id)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario con ID: " + id + " no encontrado"));
		
	}

}
