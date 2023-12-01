package dev.mrkevr.user_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.mrkevr.user_service.entity.User;
import dev.mrkevr.user_service.exception.UserNotFoundException;
import dev.mrkevr.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that username"));
	}

	public User getByEmail(String email) {
		return userRepository.findByUsername(email)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that email"));
	}
}
