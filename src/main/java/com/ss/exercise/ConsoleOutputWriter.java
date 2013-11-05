package com.ss.exercise;


public class ConsoleOutputWriter implements Runnable{
	PaymentTracker tracker;

	public ConsoleOutputWriter(PaymentTracker tracker) {
		this.tracker = tracker;
	}

	@Override
	public void run() {
		try {
			while(true){
				Thread.sleep(5*1000);
				for(String key: tracker.getPayments().keySet()){
					Payment payment = tracker.getPayments().get(key);
					System.out.println(payment.toString());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
