package com.springboot.app.jpa.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.jpa.models.dao.IUsuarioDao;
import com.springboot.app.jpa.models.entity.Role;
import com.springboot.app.jpa.models.entity.Usuario;





@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		
		
		if(usuario == null) {
			logger.error("Error login: no exiete el usuario '" + username+"' ");
			throw new UsernameNotFoundException("Username " + username + "no existe en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		
		for(Role role: usuario.getRoles()) {
			logger.info("Role: ".concat(role.getAuthority()));
				authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
			
		}
		
		if(authorities.isEmpty()) {
			logger.error("Error login:  usuario '" + username+"' no tienen roles asignados");
			throw new UsernameNotFoundException("Username  '" + username + "'no existe en el sistema!");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnable(), true , true, true, authorities);
	}

}
