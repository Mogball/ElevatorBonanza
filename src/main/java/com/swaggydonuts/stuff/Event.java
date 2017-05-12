package com.swaggydonuts.stuff;

/**
 * Created by ultim on 2017-05-12.
 *
 * An event described in the input and dispatched to the controller.
 */
public class Event {

	// When the event occurs
	public final int time;
	// The persons's start floor
	public final int start;
	// The person's destination floor
	public final int end;

	public Event(int time, int start, int end) {
		this.time = time;
		this.start = start;
		this.end = end;
	}

}
