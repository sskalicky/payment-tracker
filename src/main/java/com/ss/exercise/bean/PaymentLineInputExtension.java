package com.ss.exercise.bean;

import com.ss.exercise.bean.Payment;

public class PaymentLineInputExtension extends Payment{
	private String amountParameter;
	private boolean additionalParameters;

	public PaymentLineInputExtension() {
		super();
	}

	public void setAmountParameter(String amountParameter) {
		this.amountParameter = amountParameter;
	}

	public String getAmountParameter() {
		return amountParameter;
	}

	public boolean hasAdditionalParameters() {
		return additionalParameters;
	}

	public void setAdditionalParameters(boolean additionalParameters) {
		this.additionalParameters = additionalParameters;
	}
}
