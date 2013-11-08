package com.ss.exercise;

import com.ss.exercise.io.ConsoleInputChannel;
import com.ss.exercise.io.ConsoleOutputWriter;
import com.ss.exercise.io.FileInputChannel;
import com.ss.exercise.io.InputChannel;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PaymentTrackerBuilder {
	static final Logger logger = Logger.getLogger(PaymentTrackerBuilder.class);

	private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
	private static final Long OUTPUT_PERIOD = 60L;

	private PaymentTracker paymentTracker;
	private Args args;

	public PaymentTrackerBuilder(Args args) {
		this.args = args;
	}

	public void buildAndRunPaymentTracker() throws IOException, InterruptedException {
		this.paymentTracker = new PaymentTracker();

		Runnable consoleOutputWriter = new ConsoleOutputWriter(System.out, paymentTracker);

		final ScheduledFuture<?> outputWriterFuture = executor.scheduleAtFixedRate(consoleOutputWriter, OUTPUT_PERIOD, OUTPUT_PERIOD, SECONDS);

		buildAndExecuteConsoleInputChannel(outputWriterFuture);
		try{
			buildFileInputChannel();
		} catch (IOException ex){
			logger.error("Failed to read file:" + args.getInputFilename() + ". Reading input from this file skipped.");
		}

		executor.scheduleAtFixedRate(new Runnable() {
			public void run() {
				if(outputWriterFuture.isCancelled()){
					executor.shutdown();
				}
			}
		}, 1, 1, SECONDS);
	}

	private InputChannel buildAndExecuteConsoleInputChannel(Future outputWriterFuture) {
		ConsoleInputChannel consoleChannel = new ConsoleInputChannel(System.in, outputWriterFuture);
		consoleChannel.registerObserver(paymentTracker);
		executor.schedule(consoleChannel, 1, SECONDS);

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
