package com.vikram.blog.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vikram.blog.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 3 , message = "minimum name")
	private String name;
	@Email(message = "enter valid email")
	@NotEmpty(message = "email must be entered")
	private String email;
	
	@NotEmpty
	@Size(min = 3 , max = 10 ,message = "not valid")
	private String password;
	@NotEmpty
	private String about;
	
    private Set<RoleDto> role = new HashSet<>();
//    @JsonIgnore
//	public String getPassword() {
//    	return this.password;
//    	
//    }




}
