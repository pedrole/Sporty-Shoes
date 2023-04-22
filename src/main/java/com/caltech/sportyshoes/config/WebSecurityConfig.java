package com.caltech.sportyshoes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailService);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests()
		.antMatchers("/product/index").hasAnyAuthority("CUSTOMER","ADMIN")
		.antMatchers("/product/addProduct").hasAuthority("ADMIN")
		.antMatchers("/product/delete").hasAuthority("ADMIN")
		.antMatchers("/product/edit").hasAuthority("ADMIN")
		
		.antMatchers("/order/index").hasAnyAuthority("ADMIN")
		.antMatchers("/order/search").hasAnyAuthority("ADMIN")
		.antMatchers("/order/buy").hasAnyAuthority("CUSTOMER")
		.antMatchers("/user/index").hasAnyAuthority("ADMIN")
		.antMatchers("/user/profile").hasAnyAuthority("CUSTOMER","ADMIN")
		.antMatchers("/user/changePassword").hasAnyAuthority("CUSTOMER","ADMIN")
		.antMatchers("/brand/**").hasAnyAuthority("ADMIN")
		.and().formLogin();
		
	}
	
}
