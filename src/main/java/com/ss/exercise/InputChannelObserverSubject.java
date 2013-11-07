package com.ss.exercise;

public interface InputChannelObserverSubject {
	public void registerObserver(InputChannelObserver observer);
	public void notifyObserver();
}
