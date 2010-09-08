package org.jamescarr.amqp.example;

public class PrintingHandler {

	public void handleMessage(String text) {
		System.out.println("Received: " + text);
	}
}
