package com.swaggydonuts.stuff;

/**
 * Created by ultim on 2017-05-12.
 *
 * An elevator passenger.
 */
public class Person {

	// Event for this person
	public final Event e;
	// The total waiting time for the person
	public int time;

	public Person(Event e) {
		this.e = e;
		this.time = 0;
	}

}
