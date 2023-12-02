package dev.mrkevr.user_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import dev.mrkevr.user_service.dto.NewUserDTO;
import dev.mrkevr.user_service.entity.User;
import dev.mrkevr.user_service.service.UserService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	private final UserService userService;
	
//	@Bean
	 CommandLineRunner commandLineRunner() {
		return args -> {
			NewUserDTO newUserDTO = new NewUserDTO();
			newUserDTO.setUsername("user1");
			newUserDTO.setFullName("Jake Peralta");
			newUserDTO.setEmail("j_peralta@gmail.com");
			newUserDTO.setAbout("This is about Jake Peralta...");
			
			User savedUser = userService.addUser(newUserDTO, null);
			
			
			
			
			System.out.println(savedUser);
		};
	}
	
	
}
