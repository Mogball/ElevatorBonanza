package com.swaggydonuts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Zhang on 5/12/2017.
 */
public class Output {

	public static void writeOutput(List<OutputElement> outputs, String filename) throws IOException {

		// not sure if the thing below works the same as in C++
		int MaxTimeSim = outputs.get(outputs.size() - 1).time; // to determine the max amount of time the

		File file = new File(filename);

		PrintWriter writer = new PrintWriter(new FileWriter(file)); // makes a new file for each test case

		for (OutputElement output : outputs) {
			writer.print(output.time + ": ");
			StringBuilder sb = new StringBuilder();
			for (int[] pair : output.pairs) {
				sb.append(String.format("(%d, %d), ", pair[0], pair[1]));
			}
			String write = sb.toString();
			int i = write.lastIndexOf(',');
			if (i != -1) {
				write = write.substring(0, i);
			}
			writer.println(write);
			writer.flush();
		}
		writer.close();
	}

}

class OutputElement {
	final int time;
	final List<int[]> pairs;

	OutputElement(int time, Controller c) {
		this.time = time;
		pairs = new ArrayList<>();
		for (Car car : c.cars) {
			int[] pair = new int[2];
			pair[0] = car.floor;
			pair[1] = car.people.size();
			pairs.add(pair);
		}
	}
}