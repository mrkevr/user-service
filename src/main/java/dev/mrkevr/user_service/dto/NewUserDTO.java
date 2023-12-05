package dev.mrkevr.user_service.dto;

import dev.mrkevr.user_service.validator.UniqueEmail;
import dev.mrkevr.user_service.validator.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewUserDTO {

	@NotEmpty(message = "Field `username` must not be empty")
	@Pattern(regexp = "^(?=.{6,24}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "Invalid username format")
	@UniqueUsername
	String username;

	@NotBlank(message = "Field `email` must not be blank")
	@Email(message = "Invalid email format")
	@UniqueEmail
	String email;

	@NotEmpty(message = "Field `fullName` must not be empty")
	@Size(min = 6, max = 100, message = "Full name must be 6-100 chatacters")
	String fullName;

	@NotEmpty(message = "Field `about` must not be empty")
	@Size(min = 24, max = 300, message = "About must be 24-300 chatacters")
	String about;

//	@NotEmpty
//	@Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$\"", message = "Password must be at least eight characters and at least one letter and one number")
//	String password;
//
//	String confirmPassword;
}