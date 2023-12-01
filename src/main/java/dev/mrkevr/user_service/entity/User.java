package dev.mrkevr.user_service.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Entity(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID id;

	@Column(name = "username")
	String username;

	@Column(name = "email")
	String email;

	@Column(name = "full_name")
	String fullName;

	@Column(name = "photo_path")
	String photoPath;

	@Column(name = "about")
	String about;
}
