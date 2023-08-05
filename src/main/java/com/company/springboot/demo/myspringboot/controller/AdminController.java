package com.company.springboot.demo.myspringboot.controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.company.springboot.demo.myspringboot.dto.ProductDto;
import com.company.springboot.demo.myspringboot.entity.Category;
import com.company.springboot.demo.myspringboot.entity.Product;
import com.company.springboot.demo.myspringboot.service.CategoryService;
import com.company.springboot.demo.myspringboot.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir")+ "/src/main/resources/static/productImages";
	@Autowired
	private CategoryService categoryService; 
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String getHome() {
		return "adminHome";
	}
	@GetMapping("/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories",categoryService.findAll());
		return "categories";
	}
	@GetMapping("/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/categories/add")	
	public String PostCatAdd(@ModelAttribute("categories") Category category) {
		categoryService.save(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/categories/delete/{id}")
	public String deleteCate(@PathVariable int id) {
		categoryService.deleteById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/categories/update/{id}")
	public String updateCate(@PathVariable int id,Model model) {
		Optional<Category> category=categoryService.findById(id);

		if(category.isPresent()) {
			model.addAttribute("category",category);
			return "categoriesAdd";
		}
		return "404";
	}
	@GetMapping("/products")
	public String products(Model model) {
		
		model.addAttribute("products",productService.findAll());
		return "products";
	}
	@GetMapping("/products/add")
	public String addProducts(Model model) {
		
		model.addAttribute("productDTO",new ProductDto());
		model.addAttribute("categories",categoryService.findAll());

		return "productsAdd";
	}
	@PostMapping("/products/add")
	public String postAddProducts(@ModelAttribute ProductDto productDto,
			@RequestParam("productImage") MultipartFile file,@RequestParam("imgName")String imgName) throws IOException {
		Product product=new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setCategory(categoryService.findById(productDto.getCategoryId()).get());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setWeight(product.getWeight());
		
		String imageUUid;
		if(!file.isEmpty()) {
			imageUUid=file.getOriginalFilename();
			Path path=Paths.get(uploadDir,imageUUid);
			Files.write(path,file.getBytes());
		}
		else {
			imageUUid=imgName;
		}
		product.setImageName(imageUUid);
		productService.save(product);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteById(id);
		return "redirect:/admin/products";
	}
	@GetMapping("/product/update/{id}")
	public String updateProduct(@PathVariable Long id,Model model) {
		Product product=productService.findById(id).get();
		ProductDto productDto=new ProductDto();

		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setWeight(product.getWeight());
		productDto.setImageName(product.getImageName());
		model.addAttribute("productDTO", productDto);
		model.addAttribute("categories", categoryService.findAll());
		
		return "productsAdd";
	}
	
}
