package com.proyectotfg.rest.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Creamos esta clase para que sirva como envoltorio del repositorio para en los controladores no tener que utilizar directamente los
 * repositorios, sino que los controladores utilizaran los servicios y los servicios los repositorios. Se crean un serie de métodos basicos
 * que envuelven el repositorio.
 * @param <T> Tipo de entidad
 * @param <ID> Tipo de ID
 * @param <R> Tipo de Repositorio
 */

public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> {

	//Notación que sirve para busca el Bean necesario de forma automática
	@Autowired
	protected R repositorio;
	
	public T save(T t) {
		return repositorio.save(t);
	}
	
	public Optional<T> findById(ID id) {
		return repositorio.findById(id);
	}
	
	public List<T> findAll() {
		return repositorio.findAll();
	}
	
	
	public Page<T> findAll(Pageable pageable) {
		return repositorio.findAll(pageable);
	}
	
	public T edit(T t) {
		return repositorio.save(t);
	}
	
	public void delete(T t) {
		repositorio.delete(t);
	}
	
	public void deleteById(ID id) {
		repositorio.deleteById(id);
	}
	
	
}