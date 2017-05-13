package com.swaggydonuts;

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
		Person p = new Person(e);
		floors[e.start].people.add(p);
	}

	public void update() {
		for (Floor floor : floors) {
			List<Person> rm = new ArrayList<>();
			for (Person p : floor.people) {
				List<Car> valid = new ArrayList<>();
				for (Car car : cars) {
					if (car.isValid(p)) valid.add(car);
				}
				if (valid.isEmpty()) {
					p.time++;
					continue;
				}
				int min = 0;
				int minTC = valid.get(min).TC(p);
				for (int i = 1; i < valid.size(); i++) {
					int TC = valid.get(i).TC(p);
					if (TC < minTC) {
						min = i;
						minTC = TC;
					}
				}
				valid.get(min).addEvent(p);
				rm.add(p);
			}
			floor.people.removeAll(rm);
		}
		for (Car car : cars) {
			car.update();
		}
		updateGUI();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Controller:\nFloors:\n");
		for (int i = 0; i < floors.length; i++) {
			if (floors[i].people.isEmpty()) continue;
			sb.append(i).append(": People=").append(floors[i].people.size()).append('\n');
		}
		sb.append("Cars:\n");
		for (Car car : cars) {
			sb.append(car.toString());
		}
		return sb.toString();
	}

	public void updateGUI() {

	}

}
