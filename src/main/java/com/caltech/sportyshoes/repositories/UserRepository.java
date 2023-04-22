package com.caltech.sportyshoes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caltech.sportyshoes.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer>{
 Optional<User> findUserByUsername(String username);
 
 List<User> findByUsernameIsContaining(String titusernamele);
 
 
 
	
}
