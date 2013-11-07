package com.ss.exercise;

import com.ss.exercise.bean.Payment;
import com.ss.exercise.validator.ValidationException;

public interface PaymentBuilder {
	public Payment build() throws ValidationException;
}
