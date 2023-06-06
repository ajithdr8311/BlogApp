package com.ajith.blog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message="Username must be minimum of 4 characters")
	private String name;
	
	@NotEmpty(message="Email Id must not be empty")
	@Email(message="Email Id is not Valid")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=15, message="Password must be greater than 3 and less than 15 characters")
	private String password;
	
	@NotEmpty
	private String about;
}
