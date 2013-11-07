package com.ss.exercise.bean;

import com.ss.exercise.bean.Payment;
import com.ss.exercise.validator.ValidationException;

public interface PaymentBuilder {
	public Payment build() throws ValidationException;
}
