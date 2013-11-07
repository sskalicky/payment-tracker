package com.ss.exercise.io;

import com.ss.exercise.InputChannelObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileInputChannel implements InputChannel{
	//TODO template pattern
	InputChannelObserver inputObserver;
	String lineBuffer;

	@Override
	public void read(InputStream inputStream) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

			while ((lineBuffer = reader.readLine()) != null) {
				notifyObserver();
			}
		}
	}

	@Override
	public void registerObserver(InputChannelObserver observer) {
		this.inputObserver = observer;

	}

	@Override
	public void notifyObserver() {
		if(inputObserver != null){
			inputObserver.updateFromInputChannel(lineBuffer);
		}
	}
}
