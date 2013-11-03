package com.ss.exercise;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentTrackerShould {

	PaymentTracker paymentTracker;

	@Before
	public void init(){
		paymentTracker = new PaymentTracker();
	}

	@Test
	public void shouldAddValuesOfTheSameCurrency(){
		paymentTracker.addPayment(new Payment("EUR", new BigDecimal(100)));
		paymentTracker.addPayment(new Payment("EUR", new BigDecimal(50)));
		assertEquals(new BigDecimal(150), paymentTracker.getPayments().get("EUR").getAmount());
	}

	@Test
	public void shouldSubtractValuesOfTheSameCurrencyWhenNegativeValueIsAdded(){
		paymentTracker.addPayment(new Payment("EUR", new BigDecimal(100)));
		paymentTracker.addPayment(new Payment("EUR", new BigDecimal(-75)));
		assertEquals(new BigDecimal(25), paymentTracker.getPayments().get("EUR").getAmount());
	}
}
