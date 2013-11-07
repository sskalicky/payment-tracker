package com.ss.exercise.io;

import com.ss.exercise.InputChannelObserverSubject;

import java.io.IOException;
import java.io.InputStream;

public interface InputChannel extends InputChannelObserverSubject{

	public void read(InputStream inputStream) throws IOException, InterruptedException;
}
