package com.swaggydonuts.stuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ultim on 2017-05-12.
 */
public class Controller {

	public Car[] cars;
	public Floor[] floors;

	public Controller(int floors, int elevators) {
		this.cars = new Car[elevators];
		this.floors = new Floor[floors];
		for (int i = 0; i < floors; i++) {
			this.floors[i] = new Floor();
		}
		for (int i = 0; i < elevators; i++) {
			this.cars[i] = new Car();
		}
	}

	public void send(Event e) {

	}

}

class Floor {

	// Persons currently waiting on the floor
	List<Person> people;

	Floor() {
		people = new ArrayList<>();
	}

}