package dev.mrkevr.user_service.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidImageFileValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
public @interface ValidImageFile {

	public String message() default "Please upload a valid image file(.png .jpeg)";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
