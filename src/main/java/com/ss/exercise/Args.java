package com.ss.exercise;

import java.util.Arrays;
import java.util.List;

public class Args {
	private static final int FIRST_ARG = 0;

	private String inputFilename;

	public Args(String[] args) {
		parseArguments(Arrays.asList(args));
	}

	private void parseArguments(List<String> args) {
		if(args.size() == 1){
			inputFilename = args.get(FIRST_ARG);
		}
		if(args.size() > 1){
			throw new IllegalArgumentException("Illegal number of arguments have been passed (max 1 argument is allowed)");
		}
	}

	public String getInputFilename() {
		return inputFilename;
	}
}
