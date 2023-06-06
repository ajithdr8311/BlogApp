package com.ajith.blog.services;

import java.util.List;

import com.ajith.blog.dtos.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto c);
	List<CategoryDto> getAllCategroies();
	CategoryDto getCategoryById(int id);
	CategoryDto updateCategory(int id, CategoryDto c);
	void deleteCategory(int id);
}
