package dev.mrkevr.user_service.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dev.mrkevr.user_service.dto.NewUserDTO;
import dev.mrkevr.user_service.dto.UserResponse;
import dev.mrkevr.user_service.entity.ImageFile;
import dev.mrkevr.user_service.entity.User;
import dev.mrkevr.user_service.exception.UserNotFoundException;
import dev.mrkevr.user_service.mapper.UserMapper;
import dev.mrkevr.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;
	private final FileService fileService;
	private final UserMapper userMapper;
	
	public List<UserResponse> getAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<User> users = userRepository.findAll(pageRequest);
		return userMapper.map(users.getContent());
	}
	
	public UserResponse getById(UUID id) {
		 User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that id"));
		 return userMapper.map(user);
	}

	public UserResponse getByUsername(String username) {
		 User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that username"));
		 return userMapper.map(user);
	}

	public UserResponse getByEmail(String email) {
		 User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that email"));
		 return userMapper.map(user);
	}
	
	@Transactional
	public UserResponse addUser(NewUserDTO newUserDTO, MultipartFile newImageFile) {
		
		User user = User.builder()
			.username(newUserDTO.getUsername())
			.password(UUID.randomUUID().toString().substring(0, 6))
			.fullName(newUserDTO.getFullName())
			.email(newUserDTO.getEmail())
			.about(newUserDTO.getAbout())
			.build();
		
		String filePath = this.saveImageToDirectory(newUserDTO.getUsername(), newImageFile);	
		ImageFile imageFile = new ImageFile(user, filePath);
		user.setImageFile(imageFile);
		
		// Persist
		User savedUser = userRepository.save(user);
		return userMapper.map(savedUser);
	}
	
	// Save the image to directory and return the file path
	private String saveImageToDirectory(String username, MultipartFile imageFile) {
		String filePath = "";
		try {
			filePath = fileService.uploadImageFileToDirectory(username, imageFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return filePath;
	}
}