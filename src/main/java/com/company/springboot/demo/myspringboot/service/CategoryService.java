package com.company.springboot.demo.myspringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.springboot.demo.myspringboot.entity.Category;
import com.company.springboot.demo.myspringboot.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	public void save(Category category) {
		 
		  categoryRepo.save(category);
	}
	public Optional<Category> findById(int id) {
		 
		 return categoryRepo.findById(id);
	}
	public List<Category> findAll() {
		 
		 return categoryRepo.findAll();
	}
	public void deleteById(int id) {
		 
		  categoryRepo.deleteById(id);;
	}
	
}
