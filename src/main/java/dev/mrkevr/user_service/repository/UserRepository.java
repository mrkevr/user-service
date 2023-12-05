package dev.mrkevr.user_service.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.mrkevr.user_service.dto.UserView;
import dev.mrkevr.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
	
	// Dynamic Projection
	@Query("SELECT u FROM User u")
	List<UserView> findAllUserView();
	
	@Query("SELECT u FROM User u WHERE u.id = :id")
	Optional<UserView> findUserViewById(UUID id);
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<UserView> findUserViewByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<UserView> findUserViewByEmail(String email);
}
