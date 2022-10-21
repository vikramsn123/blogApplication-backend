package com.vikram.blog.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.vikram.blog.payload.ApiResponse;
import com.vikram.blog.payload.CategoryDto;
import com.vikram.blog.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")

public class CategoryController {
	@Autowired
	private CategoryService cats;
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategorys(@Valid @RequestBody CategoryDto catDto){
		CategoryDto cat = this.cats.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(cat, HttpStatus.CREATED);
	}
	@PutMapping("/{catID}")
	public ResponseEntity<CategoryDto> updateCategorys(@Valid @RequestBody CategoryDto catDto,@PathVariable Integer catID){
		CategoryDto cate = this.cats.updateCategory(catDto,catID);
		return new ResponseEntity<CategoryDto>(cate, HttpStatus.OK);
	}
	@GetMapping("/{catID}")
	public ResponseEntity<CategoryDto> getsinglecat(@PathVariable Integer catID){
		CategoryDto catr = this.cats.getSingleCategory(catID);
		return new ResponseEntity<CategoryDto>(catr,HttpStatus.OK);
	}
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deletecategory(@PathVariable Integer catId){
		   this.cats.deleteCategory(catId);
		   return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted",true),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAll(){
		List<CategoryDto> caty =this.cats.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(caty,HttpStatus.OK);
	}

		

	
}
