package com.caltech.sportyshoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.caltech.sportyshoes.pojo.User;
import com.caltech.sportyshoes.repositories.UserRepository;

@SpringBootApplication

public class SportyShoesApplication implements  CommandLineRunner{

	@Autowired UserRepository userRepo; 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(SportyShoesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if( userRepo.count() == 0) {
			User user = new User();
			user.setUsername("admin");
			
			user.setPassword(bCryptPasswordEncoder.encode("admin"));
			user.setRole("admin");
			userRepo.save(user);
			
		}
		
	}
	
  

}
