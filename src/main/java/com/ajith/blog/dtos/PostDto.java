package com.ajith.blog.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	
	private int id;
	
	@NotEmpty(message = "Title must not be empty")
	@Size(min = 4, message = "Title must be minimum of 4 characters long")
	private String title;
	
	@NotEmpty(message = "Content must not be empty")
	@Size(min = 4, message = "Content must be minimum of 4 characters long")
	private String content; 
	
	
	private String imageName;
	
	private Date updatedAt;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private List<CommentDto> comments = new ArrayList<>();
}
