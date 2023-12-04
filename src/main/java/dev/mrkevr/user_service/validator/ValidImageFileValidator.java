package dev.mrkevr.user_service.validator;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;

import dev.mrkevr.user_service.exception.InvalidFileException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidImageFileValidator implements ConstraintValidator<ValidImageFile, MultipartFile> {
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private  HandlerExceptionResolver exceptionResolver;
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

		// File format check
		if (value.isEmpty()
				|| (!value.getOriginalFilename().endsWith(".png") && !value.getOriginalFilename().endsWith(".jpg"))) {
			
			exceptionResolver.resolveException(null, null, context,  new InvalidFileException("FILE FORMAT ERROR"));
			
//			throw new InvalidFileException("FILE FORMAT ERROR");
//			return false;
		}

		// Image dimension check
		try {
			BufferedImage bufferedImage = ImageIO.read(value.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if (width != 150 && height != 150) {
				exceptionResolver.resolveException(null, null, context,  new InvalidFileException("IMAGE DIMENSION ERROR"));
//				throw new InvalidFileException("IMAGE DIMENSION ERROR");
//				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		// File size check (not more than 50kb)
		if (value.getSize() > 50000) {
			exceptionResolver.resolveException(null, null, context,  new InvalidFileException("FILE SIZE ERROR"));
//			throw new InvalidFileException("FILE SIZE ERROR");
//			return false;
		}

		return true;
	}
}
