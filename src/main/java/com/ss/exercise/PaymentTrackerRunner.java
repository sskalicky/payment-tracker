package com.ss.exercise;

import groovy.lang.GroovyShell;
import groovy.ui.*;
import groovy.ui.Console;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class PaymentTrackerRunner
{
    public static void main( String[] args ) throws IOException, InterruptedException {
      PaymentTracker pt = new PaymentTracker();

	    Thread t = new Thread(new ConsoleOutputWriter(pt));
//	    t.start();
	    InputStream in = System.in;
	    InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader reader = new BufferedReader(isr);

	    String line;
	    char c;
	    System.out.print("Enter payment:");


	    Scanner scanner = new Scanner(isr);
//	    Scanner input = new Scanner(System.in);
//	    char ch1,ch2;
//	    ch1 = input.next().charAt(0);
//	    ch2 = input.next().charAt(0);
//	    System.out.println("Your Frist Character is: "+ch1);
//	    System.out.println("Your Second Character is: "+ch2);

//	    while(scanner.hasNext()){
//		    System.out.println("Pre:");
//		    System.out.println("--" + scanner.next());
//		    System.out.println("Post");
//	    }

	    while(!(line = reader.readLine()).equals("quit")){
		    String[] p = line.split(" ");
		    pt.addPayment(new Payment(p[0], BigDecimal.valueOf(Double.parseDouble(p[1]))));
		    t.join(1000);
		    System.out.print("Enter payment:");

	    }

//	    while((c = (char)in.read()) != -1){
//		    System.out.println(c + "--");
//
//
//	    }

//	    byte[] tmp=new byte[1024];
//	    while(true){
//		    while(in.available()>0){
//			    int i=in.read(tmp, 0, 1024);
//			    if(i<0)
//				    break;
//			    System.out.print(new String(tmp, 0, i));
//		    }
////		    if(channel.isClosed()){
////			    System.out.println("exit-status: "+channel.getExitStatus());
////			    break;
////		    }
//		    try{Thread.sleep(1000);}catch(Exception ee){}
//	    }

//	    String input = "";
//	    char c = (char) System.in.read();
//	    while(c!=-1){
//		    c = (char)System.in.read();
//		    input += c;
//		    System.out.print("\r"+input+"***"); // \r returns the cursor to the line-beginning
//	    }



//	    for(String arg: args){
//		    System.out.println("arg:" + arg);
//	    }

//	    Terminal terminal = TerminalFacade.createSwingTerminal();
//	    terminal.enterPrivateMode();
//
//	    terminal.readInput();

//	    new Term();


    }
}
