package dev.mrkevr.user_service.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.mrkevr.user_service.entity.ImageFile;
import dev.mrkevr.user_service.repository.ImageFileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	@Value("${file_dir}")
	private String fileDirectory;
	
	private final ImageFileRepository imageFileRepository;
	
	public String uploadImageFileToDirectory(String username, MultipartFile file) throws IOException {
		String filePath = fileDirectory + File.separator + username + this.getFileExtension(file.getOriginalFilename());
		try {
			Files.copy(
				file.getInputStream(),
				Paths.get(filePath),
				StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return filePath;
	}
	
	public byte[] getImageById(UUID id) {
		
		ImageFile imageFile = imageFileRepository.findById(id).get(); // or else throw custom 404 exception
		String filePath = imageFile.getFilePath();
		
		try {
            Path imagePath = Paths.get(filePath);
            // Load the image file as a resource
            Resource resource = new UrlResource(imagePath.toUri());

            // Read the image file into an InputStream
            try (InputStream inputStream = resource.getInputStream()) {
                // Read the image bytes into a byte array
                byte[] imageBytes = inputStream.readAllBytes();
                // Return the image bytes in the response
                return imageBytes;
            }
        } catch (IOException e) {
            // Handle exceptions such as file not found or IO errors
            e.printStackTrace();
            throw new RuntimeException("File not found");
        }
	}
	
	/**
	 * Removes all characters before the last 'DOT' from the name.
	 */
	private String getFileExtension(String name) {
		String extension;
		try {
			extension = name.substring(name.lastIndexOf("."));
		} catch (Exception e) {
			extension = "";
		}
		return extension;
	}
	
}
