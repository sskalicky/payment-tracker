package com.ss.exercise.validator;

import java.util.ArrayList;
import java.util.List;

public abstract class PaymentValidator {
	List<ValidationException> validationErrors;

	protected PaymentValidator() {
		this.validationErrors = new ArrayList<>();
	}

	public void addValidationError(ValidationException validationError){
		this.validationErrors.add(validationError);
	}

	public boolean hasValidationErrors(){
		return !validationErrors.isEmpty();
	}

	public String getValidationErrorMessages(){
		StringBuffer messageBuffer = new StringBuffer("");

		for(ValidationException error: validationErrors){
			messageBuffer.append(error.getMessage());
			messageBuffer.append('\n');
		}


		return messageBuffer.toString();
	}

	protected void throwValidationExceptionsIfAny() throws ValidationException {
		if(hasValidationErrors()){
			throw new ValidationException(getValidationErrorMessages());
		}
	}

	public abstract void validate() throws ValidationException;
}
