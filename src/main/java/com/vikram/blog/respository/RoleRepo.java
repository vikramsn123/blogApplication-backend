package com.vikram.blog.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.blog.entities.Role;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
