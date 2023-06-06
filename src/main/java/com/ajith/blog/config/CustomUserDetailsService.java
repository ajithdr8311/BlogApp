package com.ajith.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajith.blog.dtos.SecurityUser;
import com.ajith.blog.dtos.UserDto;
import com.ajith.blog.entities.User;
import com.ajith.blog.exceptions.UsernameAlreadyExistsException;
import com.ajith.blog.repositories.UserRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepositories;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {

		var u = this.userRepositories.findByName(username);
		User user = u.orElseThrow(() -> new UsernameNotFoundException("Username already exists"));
		
		return new SecurityUser(user);
	}
	
	
	@Transactional
	public UserDto createUser(UserDto userDto) {
		
		var user = this.userRepositories.findByName(userDto.getName());
		if(user.isPresent()) {
			throw new UsernameAlreadyExistsException();
		}
		
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		this.userRepositories.save(this.modelMapper.map(userDto, User.class));
		
		return userDto;
	}
	
}
