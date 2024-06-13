package com.springboot.auth_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.auth_service.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

	
}
