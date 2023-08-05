package com.company.springboot.demo.myspringboot.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User implements UserDetails{

	

	public CustomUserDetails(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		 super.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		 return authorities;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
