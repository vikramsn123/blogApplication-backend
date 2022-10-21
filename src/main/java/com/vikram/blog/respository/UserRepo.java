package com.vikram.blog.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String emial); 

}
