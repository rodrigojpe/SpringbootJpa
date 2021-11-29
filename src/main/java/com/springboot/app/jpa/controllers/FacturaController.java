package com.springboot.app.jpa.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.jpa.models.entity.Cliente;
import com.springboot.app.jpa.models.entity.DetalleFactura;
import com.springboot.app.jpa.models.entity.Factura;
import com.springboot.app.jpa.models.entity.Producto;
import com.springboot.app.jpa.models.service.IClienteService;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/facturas")
@SessionAttributes("factura")
public class FacturaController {
	
	
	@Autowired
	private IClienteService clienteService;
	
	
	@PostMapping("/form")
	public String guardar(Factura factura,
			@RequestParam(name="item_id[]", required = false) Long[] itemId,
			@RequestParam(name="cantidad[]", required = false) Integer[] cantidad,
			RedirectAttributes flash,
			SessionStatus status) {
		
		for(int i= 0; i< itemId.length; i++ ) {
			Producto producto = clienteService.findProductoById(itemId[i]);
			
			
			DetalleFactura linea = new DetalleFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.AddItemFactura(linea);
		}
		
		clienteService.saveFactura(factura);
		status.setComplete();
		
		flash.addFlashAttribute("success", "Factura creada con exito");
		
		
		
		return "redirect:/ver/" + factura.getCliente().getId() ;
		
	}
	
	
	
	
	
	
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value="clienteId") Long clienteId,
			Map<String, Object> model,
			RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(clienteId);
		
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la BD");
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		model.put("factura", factura);
		model.put("titulo", "Crear Factura");
		
		return "factura/form";
		
	}
	
	
	@GetMapping(value="/cargar-productos/{term}", produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
		
		return clienteService.findByNombre(term);
	}
	
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id,
			Model model,
			RedirectAttributes flash) {
		
		Factura factura =  clienteService.fetchFacturaByIdWhitClienteWhitDetalleFacturaWhitProducto(id) ;  //clienteService.findFacturaById(id);
		
		if (factura == null) {
			flash.addFlashAttribute("error", "La factura no existe !!!");
			return "redirect:/listar";
		}
		
		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Factura : " .concat(factura.getDescripcion()));
	
		return "factura/ver";
	}
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		Factura factura = clienteService.findFacturaById(id);
		
		if(factura != null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Factura Eliominada Con exito!");
			return "redirect:/ver/" + factura.getCliente().getId();
		}
		
		
		flash.addFlashAttribute("error", "Factura no existe en la BD :(, no se pudo eliminar");
		return "redirect:/listar";
			
	}
	
	

}
