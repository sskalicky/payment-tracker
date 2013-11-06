package com.ss.exercise;

import java.util.Arrays;
import java.util.List;

public class Args {

	List<String> args;

	public Args(String[] args) {
		this.args = Arrays.asList(args);
	}

	public int getArgsCount() {
		return args.size();
	}
}
