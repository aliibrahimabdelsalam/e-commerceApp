package com.company.springboot.demo.myspringboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.springboot.demo.myspringboot.entity.Role;
import com.company.springboot.demo.myspringboot.entity.User;
import com.company.springboot.demo.myspringboot.global.GlobalData;
import com.company.springboot.demo.myspringboot.repository.RoleRepository;
import com.company.springboot.demo.myspringboot.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository RoleRepo;
	@Autowired
	private UserRepository userRepo;
	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/register")
	public String registerPost(@ModelAttribute ("User")User user,HttpServletRequest request) throws ServletException {
		String password=user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		List<Role> roles=new ArrayList<Role>();
		roles.add(RoleRepo.findById(2).get());
		user.setRoles(roles);
		userRepo.save(user);
		request.login(user.getEmail(), password);
		
		return "redirect:/";
	}

}
