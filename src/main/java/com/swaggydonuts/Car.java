package com.swaggydonuts;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by ultim on 2017-05-12.
 * <p>
 * This class describes an elevator car.
 */
public class Car {

	// The current floor the car is at
	public int floor;

	// 0, elevator is idle
	// -1, going down
	// 1, going up
	public int state;

	// state != 0 iff progress > 0

	public int progress;
	// -1 for nothing, 0 for in transit, 1 for loading
	public int moveState;

	// List of the elevator's passengers
	public List<Person> people;
	public List<Person> events;

	public Car() {
		this.floor = 0;
		this.state = 0;
		this.progress = 0;
		this.moveState = -1;
		this.people = new ArrayList<>();
		this.events = new ArrayList<>();
	}

	public int peopleCountBetweenInclusive(int start, int end) {
		List<Integer> list = new ArrayList<>();
		List<Person> tEvent = new ArrayList<>();
		tEvent.addAll(events);
		List<Person> tPeople = new ArrayList<>();
		tPeople.addAll(people);
		if (state == -1) {

			for (int i = floor; i >= end; i--) {
				List<Person> rm = new ArrayList<>();
				for (Person p : tEvent) {
					if (p.e.start == i) {
						tPeople.add(p);
						rm.add(p);
					}
				}
				tEvent.removeAll(rm);
				rm.clear();
				for (Person p : tPeople) {
					if (p.e.end == i) {
						rm.add(p);
					}
				}
				tPeople.removeAll(rm);
				list.add(tPeople.size());
			}
		} else {
			for (int i = floor; i <= end; i++) {
				List<Person> rm = new ArrayList<>();
				for (Person p : tEvent) {
					if (p.e.start == i) {
						tPeople.add(p);
						rm.add(p);
					}
				}
				tEvent.removeAll(rm);
				rm.clear();
				for (Person p : tPeople) {
					if (p.e.end == i) {
						rm.add(p);
					}
				}
				tPeople.removeAll(rm);
				list.add(tPeople.size());
			}
		}
		list.add(people.size());
		int max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) > max) max = list.get(i);
		}
		return max;

	}

	public boolean isValid(Person pr) {
		Event e = pr.e;
		if (state == 0) return true;
		if (e.start > e.end) {
			// Person going down
			if (state != -1) return false;
			if (peopleCountBetweenInclusive(e.start, e.end) >= 5) return false;
			if (moveState == 1 && floor == e.start) return true;
			if (floor > e.start) return true;
		} else {
			// Person going up
			if (state != 1) return false;
			if (peopleCountBetweenInclusive(e.start, e.end) >= 5) return false;
			if (moveState == 1 && floor == e.start) return true;
			if (floor < e.start) return true;
		}
		return false;
	}

	public int numberStopsTo(int current, int floor) {
		int stops = 0;
		for (Person p : people) {
			if (state == -1) {
				if (current > p.e.end && p.e.end > floor) stops++;
			} else {
				// state == 1
				if (current < p.e.end && p.e.end < floor) stops++;
			}
		}
		return stops;
	}

	public int timeToDestination(Person pr) {
		// isValid(e) == true
		Event e = pr.e;
		if (state == 0) {
			int deltaFloors = abs(floor - e.start);
			int waitTime = Constant.TRAVEL_TIME * deltaFloors;
			waitTime += Constant.LOADING_TIME;
			deltaFloors = abs(e.start - e.end);
			waitTime += Constant.TRAVEL_TIME * deltaFloors;
			return waitTime;
		} else {
			int currentFloor = floor;
			int waitTime = 0;
			if (moveState == 1) {
				waitTime += Constant.LOADING_TIME - progress;
			} else if (moveState == 0) {
				currentFloor++;
				waitTime += Constant.TRAVEL_TIME - progress;
			}
			int deltaFloors = abs(currentFloor - e.start);
			waitTime += Constant.TRAVEL_TIME * deltaFloors;
			waitTime += numberStopsTo(floor, e.start) * Constant.LOADING_TIME;
			waitTime += Constant.LOADING_TIME;
			waitTime += numberStopsTo(e.start, e.end) * Constant.LOADING_TIME;
			waitTime += abs(e.start - e.end) * Constant.TRAVEL_TIME;
			return waitTime;
		}
	}

	public boolean alreadyVisiting(int floor) {
		for (Person p : people) {
			if (p.e.end == floor) return true;
		}
		return false;
	}

	public int systemDegredation(Person pr) {
		// isValid(e) == true
		Event e = pr.e;
		int sdf = 0;
		boolean visitEnd = alreadyVisiting(e.end);
		boolean visitStart = alreadyVisiting(e.start);
		for (Person p : people) {
			if (state == -1) {
				if (visitStart && p.e.end < e.start) sdf += Constant.LOADING_TIME;
				if (visitEnd && p.e.end < e.end) sdf += Constant.LOADING_TIME;
			} else {
				// state == 1
				if (visitStart && p.e.end > e.start) sdf += Constant.LOADING_TIME;
				if (visitEnd && p.e.end > e.end) sdf += Constant.LOADING_TIME;
			}
		}
		return sdf;
	}

	public int totalHeuristic(Person p) {
		// isValid(e) == true
		return timeToDestination(p) + systemDegredation(p);
	}

	public boolean atStopFloor() {
		for (Person p : people) {
			if (p.e.end == floor) return true;
		}
		for (Person p : events) {
			if (p.e.start == floor) return true;
		}
		return false;
	}

	public void unloadPeople() {
		boolean change = false;
		if (people.isEmpty() && !events.isEmpty()) {
			change = true;
		}
		List<Person> remove = new ArrayList<>();
		List<Person> removeEvent = new ArrayList<>();
		for (Person p : people) {
			if (p.e.end == floor) {
				remove.add(p);
				//System.err.printf("Unloaded (%d, %d, %d) : %d @ %d%n", p.e.time, p.e.start, p.e.end, p.time, Cheat.i + 1);
				p.unloaded();
			}
		}
		for (Person p : events) {
			if (p.e.start == floor) {
				removeEvent.add(p);
				people.add(p);
				System.err.printf("Loaded (%d, %d, %d) @ %d%n", p.e.time, p.e.start, p.e.end, Cheat.i + 1);
			}
		}
		people.removeAll(remove);
		events.removeAll(removeEvent);
		if (!people.isEmpty()) {
			if (state == 1 && people.get(0).e.end < floor) change = true;
			if (state == -1 && people.get(0).e.end > floor) change = true;
		}
		if (change && events.isEmpty()) {
			changeOver = people.get(0);
			//System.err.printf("Changeover (%d, %d, %d) @ %d%n", changeOver.e.time, changeOver.e.start, changeOver.e.end, Cheat.i + 1);
		}
	}

	Person changeOver = null;

	public void update() {
		//System.err.printf("%d @ %d%n", floor, Cheat.i + 1);
		for (Person p : people) {
			p.time++;
		}
		for (Person p : events) {
			p.time++;
		}
		if (state == 0) return;
		if (moveState == 0) {
			progress++;
			if (progress == Constant.TRAVEL_TIME) {
				progress = 0;
				floor += state;
			}
			if (atStopFloor() && progress == 0) {
				moveState = 1;
				unloadPeople();
			}
		} else {
			// moveState == 1
			progress++;
			if (progress == Constant.LOADING_TIME) {
				progress = 0;
			}
			if (progress != 0) return;
			if (people.isEmpty() && events.isEmpty()) {
				moveState = -1;
				state = 0;
			} else if (changeOver != null) {
				if (changeOver.e.end > floor) {
					state = 1;
				} else {
					state = -1;
				}
				moveState = 0;
				changeOver = null;
			} else {
				moveState = 0;
			}
		}
	}

	public void addEvent(Person p) {
		// isValid(e) == true
		//System.err.printf("Event: (%d, %d, %d) : (%d, %d, %d) @ %d%n", p.e.time, p.e.start, p.e.end, floor, state, moveState, Cheat.i);
		if (moveState == 1) {
			System.err.printf("Loaded (%d, %d, %d) @ %d%n", p.e.time, p.e.start, p.e.end, Cheat.i);
			people.add(p);
			return;
		}
		events.add(p);
		if (state == 0) {
			if (p.e.start < floor) {
				state = -1;
				moveState = 0;
			} else if (p.e.start == floor) {
				events.remove(p);
				people.add(p);
				if (p.e.end > floor) {
					state = 1;
				} else {
					state = -1;
				}
				moveState = 1;
			} else {
				state = 1;
				moveState = 0;
			}
		}
	}

	@Override
	public String toString() {
		return "Car:\n" + "Passengers: " + people.size() + '\n' +
				"Waiting: " + events.size() + '\n';
	}

	public int peopleRemaining() {
		return people.size() + events.size();
	}

}
