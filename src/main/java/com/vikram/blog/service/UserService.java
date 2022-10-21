package com.vikram.blog.service;
import com.vikram.blog.payload.UserDto;

import java.util.List;

import com.vikram.blog.payload.UserDto;

public interface UserService {
	 UserDto registerUser(UserDto user);
	 UserDto createUser(UserDto  user);
	 UserDto updateUser(UserDto user,Integer userId);
	 UserDto getUserbyId(Integer userId);
     List<UserDto> getAllUser();
     void deleteUser(Integer userId);
     
}
