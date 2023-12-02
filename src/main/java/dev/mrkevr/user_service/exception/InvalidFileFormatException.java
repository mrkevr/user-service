package dev.mrkevr.user_service.exception;

public class InvalidFileFormatException extends RuntimeException {

	private static final long serialVersionUID = -8261382322632283L;

	public InvalidFileFormatException(String message) {
		super(message);
	}
}
