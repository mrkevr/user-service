package dev.mrkevr.user_service.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NewUserDTO {

	String username;

	String email;

	String fullName;

	MultipartFile photo;

	String about;
}
