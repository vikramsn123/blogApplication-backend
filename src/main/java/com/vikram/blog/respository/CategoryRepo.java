package com.vikram.blog.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.blog.entities.Categories;

public interface CategoryRepo extends  JpaRepository<Categories, Integer> {

}
