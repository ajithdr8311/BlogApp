package com.ajith.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajith.blog.dtos.ApiResponse;
import com.ajith.blog.dtos.CommentDto;
import com.ajith.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	
	@GetMapping("/post/{postId}/comments")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable Integer postId) {
		
		List<CommentDto> comments = this.commentService.getAllComments(postId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@PostMapping("/post/{postId}/user/{userId}/comment")
	public ResponseEntity<CommentDto> createComment(
			@PathVariable Integer postId,
			@PathVariable Integer userId,
			@RequestBody CommentDto commentDto
			) {
		
		CommentDto comment = this.commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteResponse(@PathVariable Integer commentId) {
		
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<>(new ApiResponse("Commnet is deleted successfully..", true), HttpStatus.OK);
	}
}
