package dev.mrkevr.user_service.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ValidImageFileValidator implements ConstraintValidator<ValidImageFile, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		
		
		
		
		if (value.isEmpty()
				|| (!value.getOriginalFilename().endsWith(".png") && !value.getOriginalFilename().endsWith(".jpg"))) {
			return false;
		}
		return true;
	}
}
