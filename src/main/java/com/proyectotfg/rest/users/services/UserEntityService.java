package com.proyectotfg.rest.users.services;

import com.proyectotfg.rest.error.exceptions.NewUserWithDifferentPasswordsException;
import com.proyectotfg.rest.service.base.BaseService;
import com.proyectotfg.rest.users.dto.CreateUserDto;
import com.proyectotfg.rest.users.model.UserEntity;
import com.proyectotfg.rest.users.model.UserRole;
import com.proyectotfg.rest.users.repos.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Notación que indica que esta clase es un servicio
@Service
// Utilizamos esta notación para inyectar por constructor
@RequiredArgsConstructor
/**
 * Esta clase extiende de la clase base para utilizar los métodos necesarios para realizar consultas en el repositorio UserEntityRepository
 */
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> {

	// Inyectamos el objeto passwordEncoder para cifrar la contraseña de los usuarios, en este caso con Bcrypt
	private final PasswordEncoder passwordEncoder;

	/**
	 * Nos permite buscar un usuario por su nombre de usuario
	 * 
	 * @param username
	 * @return
	 */
	public Optional<UserEntity> findUserByUsername(String username) {

		return this.repositorio.findByUsername(username);
	}

	/**
	 * Nos permite crear un nuevo UserEntity con rol USER y una password cifrada
	 * 
	 * @param newUser
	 * @return
	 */
	public UserEntity nuevoUsuario(CreateUserDto newUser) {

		// Verificamos si las contraseñas introducidas al crear el usuario coinciden
		if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            // Mediante builder construimos la entidad UserEntity con los atributos definidos en el CreateUserDto
			UserEntity userEntity = UserEntity.builder()
					.username(newUser.getUsername())
					.password(passwordEncoder.encode(newUser.getPassword()))
					.fullName(newUser.getFullname()).email(newUser.getEmail())
					.roles(Stream.of(UserRole.USER)
							.collect(Collectors.toSet()))
					.build();
			try {
				// Guardamos el nuevo usuario en base de datos
				return save(userEntity);
			} catch (DataIntegrityViolationException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este nombre de usuario ya existe");
			}
		} else {
			throw new NewUserWithDifferentPasswordsException();
		}

	}

}
