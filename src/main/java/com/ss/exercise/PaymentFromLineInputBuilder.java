package com.ss.exercise;

import com.ss.exercise.bean.Payment;
import com.ss.exercise.validator.LineInputPaymentValidator;
import com.ss.exercise.bean.PaymentLineInputExtension;
import com.ss.exercise.validator.PaymentValidator;
import com.ss.exercise.validator.ValidationException;

import java.math.BigDecimal;

public class PaymentFromLineInputBuilder implements PaymentBuilder{
	private static final String WORD_SEPARATOR = " ";
	private static final String EMPTY_STRING = "";

	private final PaymentLineInputExtension payment;
	private String[] lineInput;

	public PaymentFromLineInputBuilder(String lineInput) {
		this.payment = new PaymentLineInputExtension();
		this.lineInput = lineInput.split(WORD_SEPARATOR);
	}

	@Override
	public Payment build() throws ValidationException {
		buildCurrencyFromLine();
		buildAmountParameterFromLine();
		considerAdditionalParameters();
		validate();
		buildAmount();
		return payment;
	}

	private void buildCurrencyFromLine(){
		payment.setCurrency((lineInput.length > 0) ? lineInput[0].toUpperCase() : EMPTY_STRING);
	}

	private void buildAmountParameterFromLine() {
		payment.setAmountParameter((lineInput.length > 1) ? lineInput[1] : EMPTY_STRING);
	}

	private void buildAmount() {
		payment.addAmount(new BigDecimal(payment.getAmountParameter()));
	}

	private void considerAdditionalParameters() {
		if(lineInput.length > 2){
			payment.setAdditionalParameters(true);
		}
	}

	private void validate() throws ValidationException {
		PaymentValidator paymentValidator = new LineInputPaymentValidator(payment);
		paymentValidator.validate();
	}
}
