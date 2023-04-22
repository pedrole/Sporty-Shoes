package com.caltech.sportyshoes.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caltech.sportyshoes.pojo.Product;
import com.caltech.sportyshoes.pojo.User;
import com.caltech.sportyshoes.repositories.UserRepository;

@Service
public class UserDAO {
	@Autowired
	UserRepository userRepository;
	
	
	public User saveUser(User user) {
		
		return userRepository.save(user);
		
	}
	
	public void updatePassword(int id, String password) {
		User storerdUser = userRepository.findById(id).orElseThrow();
		storerdUser.setPassword(password);
		userRepository.save(storerdUser);
		
		
	}
	public Optional<User> findById(int id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	public List<User> findByUsernameIsContaining(String user) {
		 //List<User> users = userRepository.findAll();
		 List<User> users = userRepository.findByUsernameIsContaining(user);
		 return users;
		
		
	}
	
	
	

}
