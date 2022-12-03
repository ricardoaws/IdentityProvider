package com.proyectotfg.rest.error.exceptions;

public class NewUserWithDifferentPasswordsException extends RuntimeException {

	/**
	 * Creamos una excepcion para identificar cuando queremos crear un usuario para las contraseñas que introducimos al crearlo no coinciden
	 */
	private static final long serialVersionUID = -7978601526802035152L;

	public NewUserWithDifferentPasswordsException() {

		super("Las contraseñas no coinciden");
	}
	
	
	

}
