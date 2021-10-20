package com.springboot.app.jpa.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="facturas")
public class Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	private String observacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	private List<DetalleFactura> detallesFacturas;
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	private Cliente cliente;
	
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Factura() {

		this.detallesFacturas = new ArrayList<DetalleFactura>();
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DetalleFactura> getDetallesFacturas() {
		return detallesFacturas;
	}

	public void setDetallesFacturas(List<DetalleFactura> detallesFacturas) {
		this.detallesFacturas = detallesFacturas;
	}
	
	public void addDetalleFactura(DetalleFactura detalle) {
		
		detallesFacturas.add(detalle);
	}
	
	
	public Double getTotal() {
		 Double total = 0.0;
		 
		 int size = detallesFacturas.size();
		 
		 for (int i=0; i<size; i++) {
			 total += detallesFacturas.get(i).calcularImporte();
		 }
		 
		 return total;
	}
	

}
