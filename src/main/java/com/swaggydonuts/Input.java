package com.swaggydonuts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ultim on 2017-05-12.
 */
public class Input {

	public int floors;
	public int elevators;
	public List<Event>[] events;

	@SuppressWarnings("unchecked")
	public Input() {
		events = (List<Event>[]) new List[Constant.ITERATIONS];
		for (int i = 0; i < events.length; i++) {
			events[i] = new ArrayList<>();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Floors: ").append(floors).append('\n');
		sb.append("Elevators: ").append(elevators).append('\n');
		sb.append("Events: [").append('\n');
		for (List<Event> list : events) {
			if (list.isEmpty()) continue;
			sb.append('\t').append(list.get(0).time).append(": ");
			for (Event e : list) sb.append('(').append(e.start).append(", ").append(e.end).append(')').append(',');
			sb.append('\n');
		}
		sb.append(']');
		return sb.toString();
	}

}
