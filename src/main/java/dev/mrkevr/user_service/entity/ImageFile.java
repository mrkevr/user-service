package dev.mrkevr.user_service.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "image_files")
@Entity(name = "ImageFile")
public class ImageFile {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "image_file_id", updatable = false)
	UUID id;

	@OneToOne(mappedBy = "imageFile")
	User user;

	@Column(name = "file_path")
	String filePath;

	public ImageFile(User user, String filePath) {
		this.user = user;
		this.filePath = filePath;
	}
}
