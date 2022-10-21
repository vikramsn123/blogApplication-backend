package com.vikram.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vikram.blog.config.AppConstant;
import com.vikram.blog.entities.Role;
import com.vikram.blog.entities.User;
import com.vikram.blog.exception.ResourceNotFound;
import com.vikram.blog.payload.UserDto;
import com.vikram.blog.respository.RoleRepo;
import com.vikram.blog.respository.UserRepo;
import com.vikram.blog.service.UserService;
@Service
public class ServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
 
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}
	

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", " Id ", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	
	}

	@Override
	public UserDto getUserbyId(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id",userId));
		return this.userToDto(user);
	}


	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto>  userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}
    @PreAuthorize("hasRole('ADMIN')")
	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", " Id ", userId));
		this.userRepo.delete(user);


	}
	private  User dtoToUser(UserDto userDto) {
		 User user = this.modelMapper.map(userDto, User.class);
//		 user.setId(userDto.getId());
//		 user.setName(userDto.getName());
//		 user.setEmail(userDto.getEmail());
//		 user.setAbout(userDto.getAbout());
//		 user.setPassword(userDto.getPassword());
		 return user;
	}
	public UserDto userToDto(User user) {
		 UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		 userDto.setId(user.getId());
//		 userDto.setName(user.getName());
//		 userDto.setEmail(user.getEmail());
//		 userDto.setAbout(user.getAbout());
//		 userDto.setPassword(user.getPassword());
		 return userDto;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRole().add(role);
		User newUser= this.userRepo.save(user);
		
		
		return this.modelMapper.map(newUser,UserDto.class);
	}
	

}
