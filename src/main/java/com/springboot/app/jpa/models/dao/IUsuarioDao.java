package com.springboot.app.jpa.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.jpa.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);

}
