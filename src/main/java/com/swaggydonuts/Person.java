package com.swaggydonuts;

/**
 * Created by ultim on 2017-05-12.
 * <p>
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

	public void unloaded() {
		Cheat.totalTime += time;
		System.err.printf("Unloaded (%d, %d, %d) : %d @ %d%n", e.time, e.start, e.end, time, Cheat.i + 1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Person person = (Person) o;

		return time == person.time && (e != null ? e.equals(person.e) : person.e == null);
	}

	@Override
	public int hashCode() {
		int result = e != null ? e.hashCode() : 0;
		result = 31 * result + time;
		return result;
	}

}
