package com.ss.exercise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileInputReader {
	private FileInputStream fileInputStream;

	public FileInputReader(String fileName) throws FileNotFoundException {
		this.fileInputStream = new FileInputStream(fileName);
	}

	public void readInput() {
		//To change body of created methods use File | Settings | File Templates.
	}
}
