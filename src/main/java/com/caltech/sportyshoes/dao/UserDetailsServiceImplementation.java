package com.caltech.sportyshoes.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caltech.sportyshoes.pojo.MyUsersDetails;
import com.caltech.sportyshoes.pojo.User;
import com.caltech.sportyshoes.repositories.UserRepository;
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userRepository.findUserByUsername(username);
		User orElseThrow = user.orElseThrow(() -> new UsernameNotFoundException("USER is Not Found"));
		
		return new MyUsersDetails(orElseThrow);
	}

}
