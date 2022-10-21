package com.vikram.blog.service;

import java.util.List;

import com.vikram.blog.payload.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDto getSingleCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategory();
	



}
