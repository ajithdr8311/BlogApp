package com.ajith.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajith.blog.dtos.CommentDto;
import com.ajith.blog.entities.Comment;
import com.ajith.blog.entities.Post;
import com.ajith.blog.entities.User;
import com.ajith.blog.exceptions.ResourceNotFoundException;
import com.ajith.blog.repositories.CommentRepository;
import com.ajith.blog.repositories.PostRepository;
import com.ajith.blog.repositories.UserRepository;
import com.ajith.blog.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		
		Comment createdComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(createdComment, CommentDto.class);
	}
	
	
	
	@Override
	public List<CommentDto> getAllComments(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		List<Comment> comments = post.getComments();
		
		List<CommentDto> commentsDto = comments.stream()
											   .map(comment -> this.modelMapper.map(comment, CommentDto.class))
											   .collect(Collectors.toList());
		
		return commentsDto;
	}
	

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		this.commentRepo.delete(comment);
	}

}
