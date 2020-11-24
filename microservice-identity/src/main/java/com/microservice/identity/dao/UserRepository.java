package com.microservice.identity.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.microservice.identity.entity.User;

@Repository
public interface UserRepository extends AbstractBaseEntityRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	Optional<User> findByToken(String token);
	
}
