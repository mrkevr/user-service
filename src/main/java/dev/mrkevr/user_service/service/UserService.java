package dev.mrkevr.user_service.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dev.mrkevr.user_service.dto.NewUserDTO;
import dev.mrkevr.user_service.dto.UserView;
import dev.mrkevr.user_service.entity.ImageFile;
import dev.mrkevr.user_service.entity.User;
import dev.mrkevr.user_service.exception.UserNotFoundException;
import dev.mrkevr.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;
	private final FileService fileService;
	
	public List<UserView> getAll() {
		return userRepository.findAllUserView();
	}
	
	public UserView getById(UUID id) {
		return userRepository.findUserViewById(id)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that id"));
	}

	public UserView getByUsername(String username) {
		return userRepository.findUserViewByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that username"));
	}

	public UserView getByEmail(String email) {
		return userRepository.findUserViewByUsername(email)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that email"));
	}
	
	@Transactional
	public User addUser(NewUserDTO newUserDTO, MultipartFile newImageFile) {
		
		User user = User.builder()
			.username(newUserDTO.getUsername())
			.password(UUID.randomUUID().toString().substring(0, 6))
			.fullName(newUserDTO.getFullName())
			.email(newUserDTO.getEmail())
			.about(newUserDTO.getAbout())
			.build();
		
		String filePath = this.saveImageToDirectory(newUserDTO.getUsername(), newImageFile);
		
		ImageFile imageFile = ImageFile.builder()
				.user(user)
				.type("photo")
				.filePath(filePath)
				.build();
		
		user.setImageFile(imageFile);
		
		// Persist
		User savedUser = userRepository.save(user);
		return savedUser;
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
