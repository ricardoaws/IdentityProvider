package com.proyectotfg.rest.users.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Notación que indica que es una entidad
@Entity
@Table(name="usuarios")
//Con esta notación grabamos automáticamente la fecha de creación del usuario
@EntityListeners(AuditingEntityListener.class)
// Notación de Proyect Lombok que incluye estas notaciones @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor
// que incluyen los métodos básicos de una clase
@Data
// Notación de Proyect Lombok que genera un constructor sin parametros
@NoArgsConstructor
// Notación de Proyect Lombok que genera un constructor con un parámetro por cada campo de la clase
@AllArgsConstructor
// Notación de Proyect Lombok que permite que se genere el código para que la clase sea instanciable creando un objeto complejo
@Builder
// La clase implementa la interface UserDetails de Spring Security
public class UserEntity implements UserDetails {

	//Añadimos el valor para la serialización

	private static final long serialVersionUID = 6189678452627071360L;

	// Se realiza la gestión mediante un ID autogenerado, que será la clave primaría de la identidad para diferenciar cada una del resto,
	// con las 2 siguientes notaciones
	@Id
	@GeneratedValue
	private Long id;

	//Indicamos que el nombre de usuario debe ser único para que no se repita
	@Column(unique = true)
	private String username;

	private String password;
	
	private String fullName;
	
	private String email;

	//Se trata de una colección "Many-to-One" al tratarse de una enumeración
	@ElementCollection(fetch = FetchType.EAGER)
	//Con esta notación la enumeración la alamcena como un String
	@Enumerated(EnumType.STRING)
	private Set<UserRole> roles;

	//Fecha de creación del usuario
	@CreatedDate
	private LocalDateTime createdAt;

	//Esta es la expresión que es tomada por defecto para el Builder
	@Builder.Default
	private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

	// Mapeamos los Userrole en un GrantedAuthority y creamos una coleccion con la que a su vez creamos una lista con los roles del usuario
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name())).collect(Collectors.toList());
	}

	/**
	 * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	/**
	 * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
	 * cuerpo a este método
	 */	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
