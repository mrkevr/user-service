package dev.mrkevr.user_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.mrkevr.user_service.entity.ImageFile;
import dev.mrkevr.user_service.entity.User;

public interface ImageFileRepository extends JpaRepository<ImageFile, UUID> {

	Optional<ImageFile> findByUser(User user);

}
