package dev.mrkevr.user_service.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import dev.mrkevr.user_service.dto.UserResponse;
import dev.mrkevr.user_service.service.UserService;
import dev.mrkevr.user_service.validator.ValidImageFile;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Validated
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

	UserService userService;

	@GetMapping
	ResponseEntity<List<UserResponse>> getAll() {
		List<UserResponse> userResponses = userService.getAll();
		return ResponseEntity.ok(userResponses);
	}

	@GetMapping("/{id}")
	ResponseEntity<UserResponse> getByID(@PathVariable UUID id) {
		UserResponse userResponse = userService.getById(id);
		return ResponseEntity.ok(userResponse);
	}

	@PostMapping
	ResponseEntity<?> saveUser(
			@Valid @RequestPart NewUserDTO user,
			@Valid @ValidImageFile @RequestParam(name = "imageFile", required = true) MultipartFile imageFile) {
		
		UserResponse userResponse = userService.addUser(user, imageFile);
		String uri = "/api/users/" + userResponse.getId();
		String message = "User is successfully created";
		
		ResponseEntityBody body = ResponseEntityBody.builder()
			.title(HttpStatus.CREATED.toString())
			.status(HttpStatus.CREATED.value())
			.timeStamp(LocalDateTime.now())
			.message(message)
			.build();
		
		return ResponseEntity.created(URI.create(uri)).body(body);
	}
}
