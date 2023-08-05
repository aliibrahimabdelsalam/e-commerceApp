package com.company.springboot.demo.myspringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.springboot.demo.myspringboot.entity.Product;
import com.company.springboot.demo.myspringboot.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	public void save(Product product) {
		 
		  productRepository.save(product);
	}
	public Optional<Product> findById(Long id) {
		 
		 return productRepository.findById(id);
	}
	public List<Product> findAll() {
		 
		 return productRepository.findAll();
	}
	public void deleteById(Long id) {
		 
		  productRepository.deleteById(id);;
	}
	public List<Product> findAllByCategory_Id (int id){
		return productRepository.findAllByCategory_Id(id);
	}
	
}
