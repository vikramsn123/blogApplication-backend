package com.vikram.blog.controller;

import java.util.List;
import java.util.Map;

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
import com.vikram.blog.payload.UserDto;
import com.vikram.blog.service.UserService;

import lombok.Getter;

/**
 * @author VIKRAM
 *
 */
@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired
	private UserService userservice;
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userservice.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{userID}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userID") Integer UserID){
		UserDto updateUserDto = this.userservice.updateUser(userDto,UserID);
		return  ResponseEntity.ok(updateUserDto);
		
	}
	@DeleteMapping("/{userID}")
	public ResponseEntity<ApiResponse> deleteUSer(@PathVariable("userID") Integer UserID){
		this.userservice.deleteUser(UserID);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted",true),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUser(){
		return ResponseEntity.ok(this.userservice.getAllUser());
	}
	@GetMapping("/{userID}")	
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userID ){
		return ResponseEntity.ok(this.userservice.getUserbyId(userID));
	}
    

}
