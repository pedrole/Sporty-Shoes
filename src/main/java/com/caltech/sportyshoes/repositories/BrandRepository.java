package com.caltech.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caltech.sportyshoes.pojo.Brand;


public interface BrandRepository extends JpaRepository<Brand,Integer> {
	//this interface has all the crud operations with predefined methods in it 
	//in this interface if we need our own query we can frame it by using JPQL @Query	
	}