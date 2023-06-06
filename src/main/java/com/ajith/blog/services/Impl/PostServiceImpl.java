package com.ajith.blog.services.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ajith.blog.dtos.PostDto;
import com.ajith.blog.dtos.PostResponse;
import com.ajith.blog.entities.Category;
import com.ajith.blog.entities.Post;
import com.ajith.blog.entities.User;
import com.ajith.blog.exceptions.ResourceNotFoundException;
import com.ajith.blog.repositories.CategoryRepository;
import com.ajith.blog.repositories.PostRepository;
import com.ajith.blog.repositories.UserRepository;
import com.ajith.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setUpdatedAt(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post createdPost = this.postRepo.save(post);
		return this.modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Pageable page = sortDir.equalsIgnoreCase("asc") ? PageRequest.of(pageNumber, pageSize, Sort.by(sortBy))
				: PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		
		Page<Post> pagePosts = this.postRepo.findAll(page);
		List<Post> posts = pagePosts.getContent();

		List<PostDto> allPosts = new ArrayList<>();
		PostResponse postResponse = new PostResponse();
		for (Post post : posts) {
			allPosts.add(this.modelMapper.map(post, PostDto.class));
		}

		postResponse.setContent(allPosts);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(int id) {

		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(int id, PostDto postDto) {

		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName("default.png");
		post.setUpdatedAt(new Date());

		this.postRepo.save(post);
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(int id) {

		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> allPosts = new ArrayList<>();

		for (Post post : posts) {
			allPosts.add(this.modelMapper.map(post, PostDto.class));
		}

		return allPosts;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> allPosts = new ArrayList<>();

		for (Post post : posts) {
			allPosts.add(this.modelMapper.map(post, PostDto.class));
		}

		return allPosts;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		
		List<PostDto> postsDto = posts.stream()
										.map(post -> this.modelMapper.map(post, PostDto.class))
										.collect(Collectors.toList());
		
		return postsDto;
	}
	
}
