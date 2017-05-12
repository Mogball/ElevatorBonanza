package com.swaggydonuts.stuff;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ultim on 2017-05-12.
 * <p>
 * Handles reading and writing appropriate JSON data.
 */
public class Parser {

	public static Input parseInput(InputStream is) throws IOException {
			JsonReader jreader = Json.createReader(is);
			JsonObject jobject = jreader.readObject();
			Integer floors = jobject.getInt("floors");
			Integer elevators = jobject.getInt("elevators");
			JsonArray jarray = jobject.getJsonArray("events");
			List<Event> events = new ArrayList<>(2048);
			int size = jarray.size();
			for (int i = 0; i < size; i++) {
				JsonObject obj = jarray.getJsonObject(i);
				int time = obj.getInt("time");
				int start = obj.getInt("start");
				int end = obj.getInt("end");
				Event e = new Event(time, start, end);
				events.add(e);
			}
			Input input = new Input();
			input.floors = floors;
			input.elevators = elevators;
			for (Event e : events) {
				input.events[e.time].add(e);
			}
			jreader.close();
			return input;
	}

}
