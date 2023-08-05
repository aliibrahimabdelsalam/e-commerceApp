package com.company.springboot.demo.myspringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.springboot.demo.myspringboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	Optional<User> findUserByEmail(String email);

}
