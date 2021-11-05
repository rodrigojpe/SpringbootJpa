package com.springboot.app.jpa.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.jpa.models.entity.Factura;

public interface IFacturaDao  extends CrudRepository<Factura, Long>{

}
