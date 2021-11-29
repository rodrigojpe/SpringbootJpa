package com.springboot.app.jpa;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.app.jpa.app.auth.handler.LoginSuccesHandler;
import com.springboot.app.jpa.models.service.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginSuccesHandler successHandler;
	
	@Autowired
	private javax.sql.DataSource datasource;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	

	@Autowired
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		
		/*
		 * PasswordEncoder encoder = passwordEncoder; UserBuilder users =
		 * User.builder().passwordEncoder(encoder:: encode);
		 */
		
		
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);  
		
		
		/*
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
		.withUser(users.username("rodrigo").password("12345").roles("USER"));
		*/
		
		
		/*
		 * builder.jdbcAuthentication() .dataSource(datasource)
		 * .passwordEncoder(passwordEncoder)
		 * .usersByUsernameQuery("select username, password, enable from users where username = ?"
		 * )
		 * .authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where username=?"
		 * );
		 */
		 
		
		
		/*
		 * builder.userDetailsService(userDatailsService)
		 * .passwordEncoder(passwordEncoder);
		 */
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**","/listar").permitAll()
				/*
				 * .antMatchers("/ver/**").hasAnyRole("USER")
				 * .antMatchers("/upload/**").hasAnyRole("USER")
				 * .antMatchers("/form/**").hasAnyRole("ADMIN")
				 * .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
				 * .antMatchers("/facturas/**").hasAnyRole("ADMIN")
				 */
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login")
			.successHandler(successHandler)
			.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error_403");
			
	}

	
	
	
	
	
	
	
	
	
	

}
