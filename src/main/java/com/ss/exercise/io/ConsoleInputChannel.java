package com.ss.exercise.io;

import com.ss.exercise.InputChannelObserver;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

public class ConsoleInputChannel implements InputChannel, Runnable{
	static final Logger logger = Logger.getLogger(ConsoleInputChannel.class);
	public static final String QUIT_COMMAND = "quit";

	InputChannelObserver inputObserver;
	String lineBuffer;
	Future future;

	InputStream inputStream;

	public ConsoleInputChannel(InputStream inputStream, Future future){
		this.inputStream = inputStream;
		this.future = future;
	}

	@Override
	public void run() {
		try {
			read(this.inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} finally {
		}
	}

	@Override
	public void read(InputStream inputStream) throws IOException, InterruptedException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			System.out.println("Enter currency amount:");
			while(!(lineBuffer = reader.readLine()).equalsIgnoreCase(QUIT_COMMAND)){
				notifyObserver();
				System.out.println("Enter currency amount:");
			}
			future.cancel(true);
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
