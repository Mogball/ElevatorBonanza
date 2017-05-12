package com.swaggydonuts.stuff;

import java.util.List;

/**
 * Created by ultim on 2017-05-12.
 *
 * This class describes an elevator car.
 */
public class Car {

	// The current floor the car is at
	public int floor;

	// 0, elevator is idle
	// -1, going down
	// 1, going up
	public int state;

	// List of the elevator's passengers
	public List<Person> people;

}
