package com.ajith.blog.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	
	private int id;
	
	@NotEmpty(message = "Comment must not be empty")
	private String content;
	
	private UserDto user;
}
