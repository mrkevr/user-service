package dev.mrkevr.user_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import dev.mrkevr.user_service.repository.UserRepository;
import dev.mrkevr.user_service.service.UserService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	private final UserService userService;
	private final UserRepository userRepository;

//	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println(userRepository.existsByUsername("jake_peralta"));
			System.out.println(userRepository.existsByUsername("thomashelby"));
			System.out.println(userRepository.existsByUsername("thomashelbyy"));
		};
	}

}
