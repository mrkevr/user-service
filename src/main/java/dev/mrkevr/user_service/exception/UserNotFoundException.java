package dev.mrkevr.user_service.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2386515881953979386L;

	public UserNotFoundException(UUID id) {
		super("Could not find user with that id");
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
