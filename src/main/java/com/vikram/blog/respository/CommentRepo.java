package com.vikram.blog.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.blog.entities.Comments;

public interface CommentRepo  extends JpaRepository<Comments, Integer> {

}
