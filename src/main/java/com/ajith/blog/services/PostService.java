package com.ajith.blog.services;

import java.util.List;

import com.ajith.blog.dtos.PostDto;
import com.ajith.blog.dtos.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostResponse getAllPosts(Integer PageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(int id);
	
	PostDto updatePost(int id, PostDto postDto);
	
	void deletePost(int id);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
}
