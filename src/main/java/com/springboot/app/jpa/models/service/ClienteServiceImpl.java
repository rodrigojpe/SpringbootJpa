package com.springboot.app.jpa.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.jpa.models.dao.IClienteDao;
import com.springboot.app.jpa.models.dao.IFacturaDao;
import com.springboot.app.jpa.models.dao.IProductoDao;
import com.springboot.app.jpa.models.entity.Cliente;
import com.springboot.app.jpa.models.entity.Factura;
import com.springboot.app.jpa.models.entity.Producto;


@Service
public class ClienteServiceImpl  implements IClienteService {
	
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	

	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		
		clienteDao.save(cliente);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente findOne(Long id) {
		
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}
	
	  @Transactional(readOnly = true)
	  @Override 
	  public Page<Cliente> findAll(Pageable pageable) {
	  
	  return clienteDao.findAll(pageable); }

	@Transactional(readOnly = true)  
	@Override
	public List<Producto> findByNombre(String term) {
		// TODO Auto-generated method stub
		return productoDao.findByNombre(term);
	}

	@Transactional()
	@Override
	public void saveFactura(Factura factura) {
		
		facturaDao.save(factura);
	
		
	}

	@Transactional(readOnly = true) 
	@Override
	public Producto findProductoById(Long id) {
	
		return productoDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true) 
	@Override
	public Factura findFacturaById(Long id) {
		
		return facturaDao.findById(id).orElse(null);
	}
	 

}
