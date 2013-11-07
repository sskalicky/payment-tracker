package com.ss.exercise;

import com.ss.exercise.bean.Payment;
import com.ss.exercise.bean.PaymentBuilder;
import com.ss.exercise.bean.PaymentFromLineInputBuilder;
import com.ss.exercise.validator.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class PaymentTracker implements InputChannelObserver{
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

	@Override
	public void updateFromInputChannel(String input) {
		PaymentBuilder paymentBuilder = new PaymentFromLineInputBuilder(input);
		try {
			addPayment(paymentBuilder.build());
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}
	}
}
