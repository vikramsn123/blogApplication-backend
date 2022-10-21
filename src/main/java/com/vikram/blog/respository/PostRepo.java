package com.vikram.blog.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.vikram.blog.entities.Categories;
import com.vikram.blog.entities.Posts;
import com.vikram.blog.entities.User;

public interface  PostRepo extends JpaRepository<Posts, Integer> {
	List<Posts> findByUser(User user);
	List<Posts> findByCategory(Categories category);	

	
	List<Posts> searchByTitle(String title);
}
