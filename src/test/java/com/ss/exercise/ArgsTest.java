package com.ss.exercise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsTest {

	@Test
	public void shouldBeAbleToAccept0Args(){
		Args args = new Args(new String[] {});
		assertEquals(0, args.getArgsCount());
	}

	@Test
	public void shouldBeAbleToAcceptOneArg(){
		Args args = new Args(new String[] {"one"});
		assertEquals(1, args.getArgsCount());
	}

}
