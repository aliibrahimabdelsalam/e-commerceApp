package com.company.springboot.demo.myspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.springboot.demo.myspringboot.entity.Product;
import com.company.springboot.demo.myspringboot.global.GlobalData;
import com.company.springboot.demo.myspringboot.service.ProductService;

@Controller
public class CartController {

	@Autowired
	private ProductService productService;
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable Long id) {
		GlobalData.cart.add(productService.findById(id).get());
		
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String getCart(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("cart",GlobalData.cart);
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

		return "cart";
	}
	@GetMapping("/cart/removeItem/{id}")
	public String removeCart(@PathVariable int id ) {

		GlobalData.cart.remove(id);
		return "redirect:/cart";
	}
	@GetMapping("/checkout")
	public String checkout(Model model ) {
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		
		return "checkout";
	}
}
