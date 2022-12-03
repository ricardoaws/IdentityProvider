package com.proyectotfg.rest.users.repos;

import com.proyectotfg.rest.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Creación de nuestro repositorio que extiende de JpaRepository con el que crearemos una consulta para encontrar una entidad UserEntity
//por el nombre de usuario

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

	// Se configura como Optional dado que el campo username es único para cada entidad y verifica si existe un valor
	Optional<UserEntity> findByUsername(String username);

}
