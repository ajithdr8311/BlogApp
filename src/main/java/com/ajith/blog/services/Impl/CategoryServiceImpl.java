package com.ajith.blog.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajith.blog.dtos.CategoryDto;
import com.ajith.blog.entities.Category;
import com.ajith.blog.exceptions.ResourceNotFoundException;
import com.ajith.blog.repositories.CategoryRepository;
import com.ajith.blog.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto c) {
		Category category = this.modelMapper.map(c, Category.class);
		this.categoryRepo.save(category);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategroies() {
		List<CategoryDto> categoriesDto = new ArrayList<>();
		List<Category> categories = this.categoryRepo.findAll();
		
		for(Category c: categories) {
			categoriesDto.add(this.modelMapper.map(c, CategoryDto.class));
		}
		
		return categoriesDto;
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		
		Category c = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		return this.modelMapper.map(c, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(int id, CategoryDto c) {

		Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		category.setTitle(c.getTitle());
		category.setDescription(c.getDescription());
		this.categoryRepo.save(category);
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int id) {
		
		Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		this.categoryRepo.delete(category);

	}
}
