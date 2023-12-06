package dev.mrkevr.user_service.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Entity(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_id", updatable = false)
	UUID id;

	@Column(name = "username")
	String username;
	
	@Column(name = "password")
	String password;

	@Column(name = "email")
	String email;

	@Column(name = "full_name")
	String fullName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_file_id", referencedColumnName = "image_file_id")
	ImageFile imageFile;

	@Column(name = "about")
	String about;

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", fullName=" + fullName + ", about=" + about + "]";
	}
}
