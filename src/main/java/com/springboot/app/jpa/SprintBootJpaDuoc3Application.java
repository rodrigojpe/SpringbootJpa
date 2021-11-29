package com.springboot.app.jpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class SprintBootJpaDuoc3Application implements CommandLineRunner  {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	
	public static void main(String[] args)  {
		SpringApplication.run(SprintBootJpaDuoc3Application.class, args);
		
		
		
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		String password = "12345";

		for(int i=0; i<2; i++) {
			String bcryptPassword  = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
		
	}

	

}
