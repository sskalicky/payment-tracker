package com.ss.exercise;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: standa
 * Date: 11/6/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Term
{
	private Terminal term;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Term()
	{
		term = TerminalFacade.createTerminal();

		term.enterPrivateMode();

		while (true)
		{
			term.clearScreen();

			String dateString = df.format(new Date());
			term.applySGR(Terminal.SGR.ENTER_BOLD);
			term.applyForegroundColor(Terminal.Color.RED);

			show(dateString, 0, 0);

			term.flush();

			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}

	private void show(String str, int x, int y)
	{
		term.moveCursor(x, y);

		int len = str.length();

		for (int i = 0; i < len; i++)
		{
			term.putCharacter(str.charAt(i));
		}
	}
}
