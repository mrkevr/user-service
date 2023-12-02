package dev.mrkevr.user_service.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.mrkevr.user_service.dto.NewUserDTO;
import dev.mrkevr.user_service.entity.ImageFile;
import dev.mrkevr.user_service.entity.User;
import dev.mrkevr.user_service.exception.UserNotFoundException;
import dev.mrkevr.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final FileService fileService;
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User getById(UUID id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that id"));
	}

	public User getByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that username"));
	}

	public User getByEmail(String email) {
		return userRepository.findByUsername(email)
				.orElseThrow(() -> new UserNotFoundException("Could not found user with that email"));
	}

	public User addUser(NewUserDTO newUserDTO, MultipartFile newImageFile) {
		User user = User.builder()
			.username(newUserDTO.getUsername())
			.password(UUID.randomUUID().toString().substring(0, 6))
			.fullName(newUserDTO.getFullName())
			.email(newUserDTO.getEmail())
			.about(newUserDTO.getAbout())
			.build();
		
		// save image file path here
		String filePath = "";
		try {
			filePath = fileService.uploadImageFileToDirectory(newUserDTO.getUsername(), newImageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageFile imageFile = ImageFile.builder()
				.user(user)
				.type("photo")
				.filePath(filePath)
				.build();
		user.setImageFile(imageFile);
		
		User savedUser = userRepository.save(user);
		return savedUser;
	}
}
