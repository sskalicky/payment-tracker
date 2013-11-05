package com.ss.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class PaymentTrackerRunner
{
    public static void main( String[] args ) throws IOException, InterruptedException {
      PaymentTracker pt = new PaymentTracker();

	    Thread t = new Thread(new ConsoleOutputWriter(pt));
	    t.start();

	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	    String line;
	    System.out.print("Enter payment:");

	    while(!(line = reader.readLine()).equals("quit")){
		    String[] p = line.split(" ");
		    pt.addPayment(new Payment(p[0], BigDecimal.valueOf(Double.parseDouble(p[1]))));
		    t.join(100);
		    System.out.print("Enter payment:");
	    }


	    for(String arg: args){
		    System.out.println("arg:" + arg);
	    }
    }
}
