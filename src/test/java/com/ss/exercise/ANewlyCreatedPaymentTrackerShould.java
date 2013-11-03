package com.ss.exercise;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ANewlyCreatedPaymentTrackerShould {
	private PaymentTracker paymentTracker;

	@Before
	public void init(){
		this.paymentTracker = new PaymentTracker();
	}

	@Test
	public void contain0Payments(){
		assertEquals(0, paymentTracker.getPayments().size());
	}

	@Test
	public void allowNewPaymentToBeAdded(){
		paymentTracker.addPayment(new Payment("USD", new BigDecimal(100)));
		assertTrue(paymentTracker.getPayments().containsKey("USD"));
	}

	@Test
	public void containOnePaymentWhenTwoPaymentsOfTheSameCurrencyWereAdded(){
		paymentTracker.addPayment(new Payment("EUR", new BigDecimal(50)));
		paymentTracker.addPayment(new Payment("EUR", new BigDecimal(100)));
		assertEquals(1, paymentTracker.getPayments().size());
	}

	@Test
	public void containTwoPaymentsWhenTwoPaymentsOfDistinctCurrenciesWereAdded(){
		paymentTracker.addPayment(new Payment("EUR", new BigDecimal(50)));
		paymentTracker.addPayment(new Payment("GBP", new BigDecimal(100)));
		assertEquals(2, paymentTracker.getPayments().size());
	}
}
