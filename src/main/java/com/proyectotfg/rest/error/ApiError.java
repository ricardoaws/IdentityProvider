package com.proyectotfg.rest.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Configuramos nuestro modelo de error
 */

// Añadimos los métodos Get y Set por anotacion
@Setter @Getter
// Utilizamos esta notación para realizar la injección por constructor
@RequiredArgsConstructor
// Añadimos el contructor sin argumentos, el constructor con todos los argumentos y la creación de un builder
@NoArgsConstructor @AllArgsConstructor @Builder
public class ApiError {

	// Estado Http
	@NonNull
	private HttpStatus estado;
	// Fecha en formato legible
	@Builder.Default
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fecha = LocalDateTime.now();
	// Mensaje de error
	@NonNull
	private String mensaje;
	
}
