package com.ss.exercise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArgsTest {

	@Test
	public void shouldBeAbleToAccept0Args(){
		Args args = new Args(new String[] {});
		assertNull(args.getInputFilename());
	}

	@Test
	public void shouldBeAbleToAcceptOneArg(){
		String arg = "arg";
		Args args = new Args(new String[] {arg});
		assertEquals(arg, args.getInputFilename());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnExceptionWhenMoreThanOneArgIsPassed(){
		Args args = new Args(new String[] {"one", "two"});
	}

}
