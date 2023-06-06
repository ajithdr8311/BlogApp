package com.ajith.blog.services;

import java.util.List;
import com.ajith.blog.dtos.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto userDto);
	List<UserDto> getAllUsers();
	UserDto getUserById(Integer userId);
	UserDto updateUser(UserDto userDto, Integer userId);
	void deleteUser(Integer userId);
}
