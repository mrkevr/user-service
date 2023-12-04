package dev.mrkevr.user_service.exception;

public class InvalidFileException extends RuntimeException {

	private static final long serialVersionUID = -8261382322632283L;

	public InvalidFileException(String message) {
		super(message);
	}
}
