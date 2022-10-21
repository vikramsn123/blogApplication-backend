

package com.vikram.blog;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vikram.blog.config.AppConstant;
import com.vikram.blog.entities.Role;
import com.vikram.blog.respository.RoleRepo;


@SpringBootApplication
public class BlogApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		try {
		Role role = new Role();
		role.setId(AppConstant.ADMIN_USER);
		role.setName("ROLE_ADMIN");
		Role role1 = new Role();
		role1.setId(AppConstant.NORMAL_USER);
		role1.setName("ROLE_NORMAL");
		List<Role> list = List.of(role,role1);
		List<Role> saveAll = this.roleRepo.saveAll(list);
		saveAll.forEach(r->{System.out.println(r.getName());
		});
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		
	}
 
}
