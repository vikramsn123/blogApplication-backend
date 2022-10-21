package com.vikram.blog.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.blog.entities.Comments;
import com.vikram.blog.entities.Posts;
import com.vikram.blog.exception.ResourceNotFound;
import com.vikram.blog.payload.CommentDto;
import com.vikram.blog.respository.CommentRepo;
import com.vikram.blog.respository.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Posts post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFound("Post", "post id ", postId));

		Comments comment = this.modelMapper.map(commentDto, Comments.class);

		comment.setPost(post);

		Comments savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comments com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFound("Comment", "CommentId", commentId));
		this.commentRepo.delete(com);
	}

}