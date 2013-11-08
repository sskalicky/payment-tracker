package com.ss.exercise.bean;

public class PaymentLineInputExtension extends Payment{
	private String amountParameter;
	private boolean additionalParameters;

	public PaymentLineInputExtension(String currency) {
		super(currency);
	}

	public synchronized void setAmountParameter(String amountParameter) {
		this.amountParameter = amountParameter;
	}

	public synchronized String getAmountParameter() {
		return amountParameter;
	}

	public synchronized boolean hasAdditionalParameters() {
		return additionalParameters;
	}

	public synchronized void setAdditionalParameters(boolean additionalParameters) {
		this.additionalParameters = additionalParameters;
	}
}
