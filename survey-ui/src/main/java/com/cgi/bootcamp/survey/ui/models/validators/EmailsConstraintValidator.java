package com.cgi.bootcamp.survey.ui.models.validators;

import java.util.Arrays;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailsConstraintValidator implements ConstraintValidator<EmailsConstraint, String> {

	public static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	public static final Pattern VALID_EMAIL_ADDRESS_PATTERN = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);

	@Override
	public void initialize(EmailsConstraint email) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext cxt) {
		boolean result = Arrays.stream(email.split("\n")).map(elem -> elem.trim())
				.allMatch(elem -> VALID_EMAIL_ADDRESS_PATTERN.matcher(elem).matches());
		return result;
	}

}