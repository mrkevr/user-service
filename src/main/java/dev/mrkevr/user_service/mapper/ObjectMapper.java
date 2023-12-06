package dev.mrkevr.user_service.mapper;

import java.util.List;

public interface ObjectMapper<T extends Object, O extends Object> {

	O map(T target);
	
	List<O> map(List<T> target);
}
