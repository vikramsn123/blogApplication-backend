package com.vikram.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.blog.entities.Categories;
import com.vikram.blog.exception.ResourceNotFound;
import com.vikram.blog.payload.CategoryDto;
import com.vikram.blog.respository.CategoryRepo;
import com.vikram.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Categories cat = this.modelmapper.map(categoryDto, Categories.class);
		Categories add = this.categoryRepo.save(cat);
		return this.modelmapper.map(add, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category","categoryID", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        
        
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
        Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category","categoryID", categoryId));
        this.categoryRepo.deleteById(categoryId); 
	}

	@Override
	public CategoryDto getSingleCategory(Integer categoryId) {
        Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category","categoryID", categoryId));

		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
        List<Categories> cat = this.categoryRepo.findAll();
        List<CategoryDto> catr=  cat.stream().map((cate) -> this.modelmapper.map(cate, CategoryDto.class)).collect(Collectors.toList());
		return catr;
	}

}
