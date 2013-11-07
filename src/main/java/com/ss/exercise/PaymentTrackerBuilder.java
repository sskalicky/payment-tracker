package com.ss.exercise;

import com.ss.exercise.io.ConsoleInputChannel;
import com.ss.exercise.io.ConsoleOutputWriter;
import com.ss.exercise.io.FileInputChannel;
import com.ss.exercise.io.InputChannel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PaymentTrackerBuilder {
	private PaymentTracker paymentTracker;
	private Args args;

	Thread t;

	public PaymentTrackerBuilder(Args args) {
		this.args = args;
	}

	public void buildAndRunPaymentTracker() throws IOException, InterruptedException {
		this.paymentTracker = new PaymentTracker();

		//TODO
		t = new Thread(new ConsoleOutputWriter(paymentTracker));
		t.start();

		InputChannel fileChannel = buildFileInputChannel();
		InputChannel consoleChannel = buildConsoleInputChannel();

		consoleChannel.read(System.in);
	}

	private InputChannel buildConsoleInputChannel() {
		ConsoleInputChannel consoleChannel = new ConsoleInputChannel(t);
		consoleChannel.registerObserver(paymentTracker);

		return consoleChannel;
	}

	private InputChannel buildFileInputChannel() throws IOException {
		FileInputChannel result = null;
		if(args.getInputFilename() != null){
			Path file = Paths.get(args.getInputFilename());
			FileInputChannel fileChannel = new FileInputChannel();
			fileChannel.registerObserver(paymentTracker);
			fileChannel.read(Files.newInputStream(file));
		}
		return result;
	}
}
