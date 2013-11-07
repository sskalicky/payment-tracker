package com.ss.exercise;

import com.ss.exercise.io.FileInputChannel;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileInputChannelShould {

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

	@Test(expected = NoSuchFileException.class)
	public void throwAnExceptionWhenFileDoesNotExist() throws IOException {
		Path file = Paths.get(notExistingFileName);
		FileInputChannel fileChannel = new FileInputChannel();
		fileChannel.read(Files.newInputStream(file));
	}

	@Test
	public void readMultiLineFile() throws IOException {
		FileInputChannel fileInputReader = new FileInputChannel();
		fileInputReader.read(new FileInputStream(testInputFile));
	}

	private void buildTestFile() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(testInputFile));
		out.write("USD 1000\n");
		out.write("EUR 100\n");
		out.write("USD 200");
		out.close();
	}
}
