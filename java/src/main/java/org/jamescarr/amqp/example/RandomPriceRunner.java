package org.jamescarr.amqp.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RandomPriceRunner {
	public static void main(String... args){
		new AnnotationConfigApplicationContext(IntervalBasedConfiguration.class);
	}
}
