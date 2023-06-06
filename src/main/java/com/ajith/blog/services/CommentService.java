package com.ajith.blog.services;

import java.util.List;

import com.ajith.blog.dtos.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
	
	List<CommentDto> getAllComments(Integer postId);
	
	void deleteComment(Integer commentId);
}
