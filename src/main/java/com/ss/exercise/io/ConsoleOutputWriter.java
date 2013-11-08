package com.ss.exercise.io;

import com.ss.exercise.bean.Payment;
import com.ss.exercise.PaymentTracker;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

public class ConsoleOutputWriter implements Runnable{
	static final Logger logger = Logger.getLogger(ConsoleOutputWriter.class);

	private static final String NEW_LINE = "\n";
	OutputStream outputStream;
	PaymentTracker tracker;

	public ConsoleOutputWriter(OutputStream outputStream, PaymentTracker tracker) {
		this.tracker = tracker;
		this.outputStream = outputStream;
	}

	@Override
	public void run() {
		try {
			if(!tracker.getPayments().isEmpty()){
				printHeader();
			}

			for(String key: tracker.getPayments().keySet()){
				Payment payment = tracker.getPayments().get(key);
				if(!zeroAmountFilter(payment)) {
						outputStream.write(
										new StringBuffer("")
														.append(payment.toString())
														.append(NEW_LINE)
														.toString().getBytes());
				}
			}

			if(!tracker.getPayments().isEmpty()){
				printFooter();
			}

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private void printHeader() throws IOException {
		outputStream.write(
						new StringBuffer("------- Payment tracker list -------")
										.append(NEW_LINE)
										.toString().getBytes());
	}

	private void printFooter() throws IOException {
		outputStream.write(
						new StringBuffer("-------------------------------------")
										.append(NEW_LINE)
										.toString().getBytes());
	}

	private static boolean zeroAmountFilter(Payment payment){
		return payment.getAmount().equals(BigDecimal.ZERO);
	}
}
