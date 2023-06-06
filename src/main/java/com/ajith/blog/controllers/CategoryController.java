package com.ajith.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajith.blog.dtos.ApiResponse;
import com.ajith.blog.dtos.CategoryDto;
import com.ajith.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {

		List<CategoryDto> categories = this.categoryService.getAllCategroies();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {

		CategoryDto category = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto c) {

		CategoryDto createdCategory = this.categoryService.createCategory(c);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto c,
			@PathVariable Integer categoryId) {

		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryId, c);
		return new ResponseEntity<>(updatedCategory, HttpStatus.CREATED);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Category deleted Suceessfully..", true), HttpStatus.OK);
	}
}
