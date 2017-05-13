package com.swaggydonuts;


import java.util.List;
import java.io.IOException;
import java.io.InputStream;

public class Main {

	public static void print(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {

		InputStream is = Main.class.getResourceAsStream("elevator_practice1.json");
		try {
			Input input = Parser.parseInput(is);
			Controller controller = new Controller(input.floors, input.elevators);
			for (int i = 0; i < Constant.ITERATIONS; i++) {
				Cheat.i = i;
				List<Event> events = input.events[i];
				for (Event event : events) controller.send(event);
				controller.update();
			}
			print(controller);
			print(Cheat.totalTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
