package dev.mrkevr.user_service.mapper;

import java.util.Collection;

public interface ObjectMapper<T extends Object, O extends Object> {

	O map(T target);
	
	Collection<O> map(Collection<T> target);
}
