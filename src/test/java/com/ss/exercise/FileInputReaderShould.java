package com.ss.exercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;


public class FileInputReaderShould {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File testInputFile;
	private String notExistingFileName;

	@Before
	public void init() throws IOException {
		testInputFile = folder.newFile("testFile.txt");
		buildTestFile();
		notExistingFileName = "dummy.txt";
	}

	@Test(expected = FileNotFoundException.class)
	public void throwAnExceptionWhenFileDoesNotExist() throws FileNotFoundException {
		FileInputReader fileInputReader = new FileInputReader(notExistingFileName);
	}

	@Test
	public void readMultiLineFile() throws FileNotFoundException {
		FileInputReader fileInputReader = new FileInputReader(testInputFile.getPath());
		fileInputReader.readInput();
	}

	private void buildTestFile() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testInputFile));
		out.write("USD 1000\n");
		out.write("EUR 100\n");
		out.write("USD 200");
		out.close();
	}
}
