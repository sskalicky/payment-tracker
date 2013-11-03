package com.ss.exercise;

import java.util.HashMap;
import java.util.Map;

public class PaymentTracker {
	private Map<String, Payment> payments;

	public PaymentTracker() {
		this.payments = new HashMap<>();
	}

	public Map<String, Payment> getPayments() {
		return this.payments;
	}

	public void addPayment(Payment payment) {
		if(payments.containsKey(payment.getCurrency())){
			payments.get(payment.getCurrency()).addAmount(payment.getAmount());
		} else {
			payments.put(payment.getCurrency(), payment);
		}

	}
}
