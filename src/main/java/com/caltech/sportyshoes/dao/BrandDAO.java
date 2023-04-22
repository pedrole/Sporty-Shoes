package com.caltech.sportyshoes.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caltech.sportyshoes.pojo.Brand;
import com.caltech.sportyshoes.pojo.Product;
import com.caltech.sportyshoes.repositories.BrandRepository;

@Service
public class BrandDAO {

	@Autowired
	BrandRepository repo;

	public List<Brand> getAll() {

		return repo.findAll();
	}

	public Brand insert(Brand brand) {
		return repo.save(brand);
	}

	public Optional<Brand> getByid(int id) {
		return repo.findById(id);
	}

	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public Brand update(Brand brand) {
		
		Brand storedBrand = repo.findById(brand.getId()).orElse(null);
		storedBrand.setName(brand.getName());
		
		return repo.save(storedBrand);
		
		
	}

}
