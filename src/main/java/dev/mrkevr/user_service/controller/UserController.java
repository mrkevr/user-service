package dev.mrkevr.user_service.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.mrkevr.user_service.dto.NewUserDTO;
import dev.mrkevr.user_service.dto.ResponseEntityBody;
import dev.mrkevr.user_service.entity.User;
import dev.mrkevr.user_service.service.UserService;
import dev.mrkevr.user_service.validator.ValidImageFile;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

	UserService userService;

	@GetMapping
	ResponseEntity<List<User>> getAll() {
		List<User> users = userService.getAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	ResponseEntity<User> getByID(@PathVariable UUID id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	ResponseEntity<?> saveUser(
			@Valid @RequestPart NewUserDTO dto,
			@ValidImageFile @RequestParam(name = "imageFile", required = true) MultipartFile imageFile) {
		
		User savedUser = userService.addUser(dto, imageFile);
		String uri = "/api/users/" + savedUser.getId();
		String message = "User is successfully created";
		Map<String, Object> properties = Map.of("uri", uri, "message", message);
		
		ResponseEntityBody body = ResponseEntityBody.builder()
			.title(HttpStatus.CREATED.toString())
			.status(HttpStatus.CREATED.value())
			.timeStamp(LocalDateTime.now())
			.properties(properties)
			.build();
		
		return ResponseEntity.created(URI.create(uri)).body(body);
	}
}
