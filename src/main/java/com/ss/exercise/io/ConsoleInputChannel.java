package com.ss.exercise.io;

import com.ss.exercise.InputChannelObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleInputChannel implements InputChannel{
	InputChannelObserver inputObserver;
	String lineBuffer;
	Thread t;

	public ConsoleInputChannel(Thread t){
		this.t = t;
	}

	@Override
	public void read(InputStream inputStream) throws IOException, InterruptedException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			//TODO remove hardcoded quit
			while(!(lineBuffer = reader.readLine()).equals("quit")){
				notifyObserver();
//				String[] p = line.split(" ");
//				System.out.println(line);
//				pt.addPayment(new Payment(p[0], BigDecimal.valueOf(Double.parseDouble(p[1]))));
				t.join(1000);
//				System.out.print("Enter payment:");

			}
		}
	}

	@Override
	public void registerObserver(InputChannelObserver observer) {
		this.inputObserver = observer;
	}

	@Override
	public void notifyObserver() {
		inputObserver.updateFromInputChannel(lineBuffer);
	}
}
