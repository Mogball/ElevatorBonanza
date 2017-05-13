package com.swaggydonuts;

/**
 * Created by ultim on 2017-05-12.
 * <p>
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Event event = (Event) o;

		return time == event.time && start == event.start && end == event.end;
	}

	@Override
	public int hashCode() {
		int result = time;
		result = 31 * result + start;
		result = 31 * result + end;
		return result;
	}
}
