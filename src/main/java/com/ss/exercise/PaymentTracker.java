package com.ss.exercise;

import java.math.BigDecimal;
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
		//TODO validate
		//TODO addPayment
		String[] arr = input.split(" ");
		addPayment(new Payment(arr[0], new BigDecimal(Integer.parseInt(arr[1]))));
	}
}
