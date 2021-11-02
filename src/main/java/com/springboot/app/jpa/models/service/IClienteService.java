package com.springboot.app.jpa.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.app.jpa.models.entity.Cliente;
import com.springboot.app.jpa.models.entity.Producto;

public interface IClienteService {
	
	
	public Page<Cliente> findAll(Pageable pageable);
	 
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	
	public List<Producto> findByNombre(String term);

}
