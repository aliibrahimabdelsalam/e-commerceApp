package com.company.springboot.demo.myspringboot.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@Column(nullable=false)
	private String name;
	@ManyToMany(mappedBy = "roles")
	private List<User>users;
	public Role(int id, String name, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
