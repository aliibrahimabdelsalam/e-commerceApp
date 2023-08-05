package com.company.springboot.demo.myspringboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.springboot.demo.myspringboot.entity.User;
import com.company.springboot.demo.myspringboot.entity.CustomUserDetails;

import com.company.springboot.demo.myspringboot.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user=userRepo.findUserByEmail(username);
		return user.map(CustomUserDetails::new).get();
	}

}
