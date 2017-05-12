package com.swaggydonuts.stuff;

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
		events = (List<Event>[]) new List[1000];
	}

}
