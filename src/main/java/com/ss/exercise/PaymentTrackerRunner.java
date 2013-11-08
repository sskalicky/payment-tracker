package com.ss.exercise;

import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.util.Properties;

public class PaymentTrackerRunner
{
    public static void main( String[] args ) throws IOException, InterruptedException {
	    //log4j basic configurator
	    BasicConfigurator.configure();

	    PaymentTrackerBuilder builder = new PaymentTrackerBuilder(new Args(args));
	    builder.buildAndRunPaymentTracker();
    }
}
