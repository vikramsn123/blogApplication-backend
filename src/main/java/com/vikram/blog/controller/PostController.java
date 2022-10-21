package com.vikram.blog.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vikram.blog.payload.ApiResponse;
import com.vikram.blog.payload.PostDto;
import com.vikram.blog.payload.PostResponse;
import com.vikram.blog.service.FileService;
import com.vikram.blog.service.impl.PostServiceImpl;



@RestController
@RequestMapping("/api/v1")
public class PostController{

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	@Autowired
	private PostServiceImpl post;
	@PostMapping("/users/{userID}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto  postDto, @PathVariable("userID") Integer userId,@PathVariable Integer categoryId){
		PostDto po = this.post.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(po,HttpStatus.CREATED);
	}
	@GetMapping("/users/{userID}/post")
	public ResponseEntity<List<PostDto>> getPostbyUserID(@PathVariable("userID") Integer userId){
		List<PostDto> post = this.post.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
			
	}
	@GetMapping("/category/{categoryID}/post")
	public ResponseEntity<List<PostDto>> getPostbyCategoryID(@PathVariable("categoryID") Integer categoryId){
		List<PostDto> post = this.post.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}
	@GetMapping("/post/{postID}")
	public ResponseEntity<PostDto> getPostbyID(@PathVariable("postID") Integer postID){
		PostDto poi = this.post.getPostById(postID);
		return new ResponseEntity<PostDto>(poi,HttpStatus.OK);
	}
   @DeleteMapping("/post/{postID}")
   public ResponseEntity<ApiResponse> deletePostByID(@PathVariable("postID") Integer postID){
	             this.post.deletePost(postID);
	   return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted",true) , HttpStatus.OK);
   }
   @PutMapping("/post/{postID}")
   public ResponseEntity<PostDto> updatePostbyID(@RequestBody PostDto postDto,@PathVariable("postID")Integer postID){
	   PostDto posg = this.post.updatePost(postDto, postID);
	   return new ResponseEntity<PostDto>(posg,HttpStatus.OK);
   }
   @GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		PostResponse postResponse = this.post.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
		List<PostDto> result = this.post.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDto postDto = this.post.getPostById(postId);
		
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.post.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
    //method to serve files
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream())   ;

    }
	




}
