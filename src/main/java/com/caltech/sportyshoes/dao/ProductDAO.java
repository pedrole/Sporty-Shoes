package com.caltech.sportyshoes.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caltech.sportyshoes.pojo.Product;
import com.caltech.sportyshoes.repositories.ProductRepository;

@Service
public class ProductDAO {
	
	//autowire - automatically trigger the object creation (no need to write ProductRepository repo=new ProductRepository())
	@Autowired
	ProductRepository repo;
	
//insert
	public Product insert(Product product) {
		return repo.save(product);
	}

	
//retrieve	
	public List<Product> getall(){
		return repo.findAll();
	}
	
	public Optional<Product> findById(int id) {
		Optional<Product> product = repo.findById(id);
		return product;
	}
	
	public void delete(int id) {
				repo.deleteById(id);
	}
	
	public Product update(Product product) {
		
		Product storedProduct = repo.findById(product.getId()).orElse(null);
		storedProduct.setName(product.getName());
		storedProduct.setCost(product.getCost());
		storedProduct.setBrand(product.getBrand());
		return repo.save(storedProduct);
		
		
	}
	
	
	
	

}

