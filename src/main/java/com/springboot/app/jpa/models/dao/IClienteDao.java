package com.springboot.app.jpa.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.app.jpa.models.entity.Cliente;

public interface IClienteDao  extends PagingAndSortingRepository<Cliente, Long>{
	
	
	
	
	

}
