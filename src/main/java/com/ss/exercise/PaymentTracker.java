package com.ss.exercise;

import com.ss.exercise.bean.Payment;
import com.ss.exercise.bean.PaymentBuilder;
import com.ss.exercise.bean.PaymentFromLineInputBuilder;
import com.ss.exercise.validator.ValidationException;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PaymentTracker implements InputChannelObserver{
	static final Logger logger = Logger.getLogger(PaymentTracker.class);
	private ConcurrentMap<String, Payment> payments;


	public PaymentTracker() {
		this.payments = new ConcurrentHashMap<>();
	}

	public ConcurrentMap<String, Payment> getPayments() {
		return this.payments;
	}

	public void addPayment(Payment payment) {
		if(payments.containsKey(payment.getCurrency())){
			payments.get(payment.getCurrency()).addAmount(payment.getAmount());
		} else {
			payments.putIfAbsent(payment.getCurrency(), payment);
		}
	}

	@Override
	public void updateFromInputChannel(String input) {
		PaymentBuilder paymentBuilder = new PaymentFromLineInputBuilder(input);
		try {
			addPayment(paymentBuilder.build());
		} catch (ValidationException e) {
			logger.warn(e.getMessage());
		}
	}
}
