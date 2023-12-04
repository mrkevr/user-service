package dev.mrkevr.user_service.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseEntityBody {

	String title;

	int status;

	LocalDateTime timeStamp;

	Map<String, Object> properties;
}
