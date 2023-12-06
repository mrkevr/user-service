package dev.mrkevr.user_service.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.mrkevr.user_service.dto.UserResponse;
import dev.mrkevr.user_service.entity.User;

@Component
public class UserMapper implements ObjectMapper<User, UserResponse>{
	
	private final String IMAGE_SERVCE_ENDPOINT = "http://localhost:9001/api/images/";
	
	@Override
	public UserResponse map(User target) {
		
		String imageUrl = IMAGE_SERVCE_ENDPOINT+target.getImageFile().getId();
		
		UserResponse userResponse = UserResponse.builder()
			.id(target.getId())
			.username(target.getUsername())
			.fullName(target.getFullName())
			.email(target.getEmail())
			.about(target.getAbout())
			.imageUrl(imageUrl)
			.build();
		
		return userResponse;
	}

	@Override
	public Collection<UserResponse> map(Collection<User> target) {
		return target.stream()
				.map(user -> map(user))
				.collect(Collectors.toList());
	}

}
