package com.company.springboot.demo.myspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.springboot.demo.myspringboot.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

	
}
