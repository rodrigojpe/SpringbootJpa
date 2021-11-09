package com.springboot.app.jpa.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.app.jpa.models.entity.Cliente;

public interface IClienteDao  extends PagingAndSortingRepository<Cliente, Long>{
	
	
	@Query("select c from Cliente c join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWhitFacturas(Long id);
	
	
	

}
