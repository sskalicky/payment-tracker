package com.ss.exercise.validator;

import com.ss.exercise.bean.PaymentLineInputExtension;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineInputPaymentValidator extends PaymentValidator{
	private static final String CURRENCY_SYMBOL_REGEX = "^[A-Z]{3}$";
	PaymentLineInputExtension payment;

	public LineInputPaymentValidator(PaymentLineInputExtension payment) {
		this.payment = payment;
	}

	public void validate() throws ValidationException {
		validateLackOfParameters();
		validateTooManyParameters();
		validateCurrency();
		validateAmount();
		throwValidationExceptionsIfAny();
	}

	private void validateLackOfParameters() {
		if(payment.getCurrency() == null || payment.getCurrency().isEmpty()){
			addValidationError(new ValidationException("Currency symbol is missing!"));
		}
	}

	private void validateTooManyParameters() {
		if(payment.hasAdditionalParameters()){
			//TODO hard coded messages
			addValidationError(new ValidationException("Too many words in input!"));
		}
	}

	private void validateCurrency() {
		Pattern pattern = Pattern.compile(CURRENCY_SYMBOL_REGEX);
		Matcher matcher = pattern.matcher(payment.getCurrency());
		if(!matcher.matches()){
			addValidationError(new ValidationException("Wrong Currency symbol format (should be 3 letter code)!"));
		}
	}

	private void validateAmount() {
		try {
			new BigDecimal(payment.getAmountParameter());
		} catch (NumberFormatException ex) {
			addValidationError(new ValidationException("Wrong number format of Amount parameter!"));
		}
	}
}
