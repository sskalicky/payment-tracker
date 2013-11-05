package com.ss.exercise;

import java.math.BigDecimal;

public class Payment {
	private String currency;
	private BigDecimal amount;

	public Payment(String currency, BigDecimal amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void addAmount(BigDecimal amount) {
		this.amount = this.amount.add(amount);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.currency);
		buffer.append(" ");
		buffer.append(this.amount);

		return buffer.toString();
	}
}
