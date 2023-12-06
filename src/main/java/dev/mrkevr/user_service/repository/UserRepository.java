package dev.mrkevr.user_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.mrkevr.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
}
