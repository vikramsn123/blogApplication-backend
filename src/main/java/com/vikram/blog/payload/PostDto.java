package com.vikram.blog.payload;

import java.sql.Date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

		private Integer postId;
		
		private String title;
		
		private String content;
		
		private String imageName;
		
		private Date addedDate;	
		
		private CategoryDto category;

		private UserDto user;
		
		

		
		
		
		
		
		
}


