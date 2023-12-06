package dev.mrkevr.user_service.dto;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

	UUID id;

	String username;

	String email;

	String fullName;

	String about;

	String imageUrl;
}
