package com.ajith.blog.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int id;
	
	@NotEmpty(message = "title must not be empty")
	@Size(min = 4, message = "title must be minimum 4 characters long")
	private String title;
	
	@NotEmpty(message = "description must not be empty")
	@Size(min = 6, message = "Description must be 6 characters long")
	private String description;
}
