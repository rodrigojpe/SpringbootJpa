package com.springboot.app.jpa.controllers;


import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.tomcat.jni.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.springboot.app.jpa.models.entity.Cliente;
import com.springboot.app.jpa.models.service.IClienteService;

import com.springboot.app.jpa.util.paginator.PageRender;


@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	
	@Autowired
	private IClienteService clienteServicio;
	
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	
	@GetMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename){
		Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			recurso =  new UrlResource(pathFoto.toUri());
			if(!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException("Error:  La imagen no se pudo cargar" + pathFoto.getFileName());
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename()+ "\"")
				.body(recurso);
		
		
	}
	
	
	
	

	
	@RequestMapping(value= {"listar", "/"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue = "0") int page,  Model model) {
				
		 Pageable pageRequest = PageRequest.of(page, 5);
		 
		 Page<Cliente> clientes = clienteServicio.findAll(pageRequest);
		 PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		
		System.out.println(clienteServicio.findAll());
		return "listar";
	}
	
	@GetMapping(value="/form")
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
		
	}
	
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String grabar(@Valid Cliente cliente, BindingResult result, Model model,@RequestParam("file") MultipartFile foto ,SessionStatus status,RedirectAttributes flash ) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		
		
		if (!foto.isEmpty()) {
			
			String uniqueFileName = UUID.randomUUID().toString()+ "_" + foto.getOriginalFilename();
			
			Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
			
			Path rootAbsolutePath = rootPath.toAbsolutePath();
		
			
			try {
				
				Files.copy(foto.getInputStream(), rootAbsolutePath);
				
				flash.addFlashAttribute("info", "Imagen subida correctamente");
				
				cliente.setFoto(uniqueFileName);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con Éxito" : "Cliente creado con Éxito";

		clienteServicio.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	@GetMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String,Object> model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if (id > 0) {
			cliente = clienteServicio.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El Cliente no existe en la BBDD");
			}
		} else {
			flash.addFlashAttribute("error", "El id no puede ser cero!");
			return "redirect:/listar";
		}
		
		model.put("titulo", "Editar Cliente");
		model.put("cliente", cliente);
		
		
		return "form";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			Cliente cliente = clienteServicio.findOne(id);
			clienteServicio.delete(id);
			flash.addFlashAttribute("success", "Cliente Eliminado con Éxito");
			
			Path rootPath = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
			java.io.File archivo = rootPath.toFile();	
			
			if(archivo.exists() && archivo.canRead()) {
				
				if(archivo.delete()) {
					flash.addFlashAttribute("info","Archivo eliminado "+ cliente.getFoto() );
				}
			}
				
		}
		return "redirect:/listar";
	}
	
	
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente =  clienteServicio.fetchByIdWhitFacturas(id);   //clienteServicio.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "Cliente no existe en la BD");
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Detalles del cliente: " + cliente.getNombre());
		
		return "ver";
		
	}
	
	

}
