package dev.mrkevr.user_service.dto;

import java.util.UUID;

public interface UserView {

	UUID getId();

	String getUsername();

	String getEmail();

	String getFullName();

	String getAbout();
}
