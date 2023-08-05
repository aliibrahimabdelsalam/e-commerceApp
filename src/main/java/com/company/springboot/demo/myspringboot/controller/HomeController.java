package com.company.springboot.demo.myspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.springboot.demo.myspringboot.entity.Product;
import com.company.springboot.demo.myspringboot.global.GlobalData;
import com.company.springboot.demo.myspringboot.service.CategoryService;
import com.company.springboot.demo.myspringboot.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	@GetMapping({"/","/home"})
	public String getHome(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());

		return "index";
	}
	@GetMapping("/shop")
	public String viewShop(Model model ) {
		model.addAttribute("products",productService.findAll());
		model.addAttribute("categories",categoryService.findAll());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String viewCategory(@PathVariable int id,Model model ) {
		model.addAttribute("products",productService.findAllByCategory_Id(id));
		model.addAttribute("categories",categoryService.findAll());
		model.addAttribute("cartCount",GlobalData.cart.size());		
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable Long id,Model model ) {
		model.addAttribute("product",productService.findById(id).get());		
		return "viewProduct";
	}

	
	
}
