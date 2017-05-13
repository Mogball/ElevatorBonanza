package com.swaggydonuts.stuff;

import java.io.*;


/**
 * Created by Kevin Zhang on 5/12/2017.
 */


public class Output {
    // uhh tried calling from other classes
    public Car cars = new Car();
    public ReadFile file = new ReadFile();
    public double floor;
    public int people;
    public int state;

    // prob need to write code to call the other classes needed but idk xd


    public int timeToComplete; // want to get continously get time from the controller class

    public int numTest; // the number of test runs reading from file


    public int MaxTimeSim; // to determine the maximum amount of time the
    // program will run, 1000s or
    // time to finish all the actions, whichever is higher

    public void OutputToFile(Controller controller, String filename) throws IOException {

        timeToComplete = 1500;
        // not sure if the thing below works the same as in C++
        MaxTimeSim = (timeToComplete > 1000 ? timeToComplete : 1000); // to determine the max amount of time the

        File file = new File(filename);

        PrintWriter writer = new PrintWriter(new FileWriter(file)); // makes a new file for each test case

        for (int i = 0; i <= MaxTimeSim; i++) {
            // for each second of the simulation, a line needs to put outputted
            writer.print(i + ": ");
`
            for (int j = 0; j < cars; j++) { // to print out for each car
                // below trying to call the values from the car class, for each elevator in the building

                floor = Car.getFloor(); // trying to call the floor the car is currently on

                people = Car.getPeople(); // trying to call how many people are currently in the car

                state = Car.getState(); // trying to get the state of the car, going up, down or not moving

                // is to accommodate the cars in transition between floors, round the floor value to the nearest int
                // to determine the most recent floor it visited, rounding up or down depending on the state of the car
                if (state == 1) {
                    floor = Math.floor(floor);
                } else if (state == -1) {
                    floor = Math.ceil(floor);
                }
                writer.printf("(", floor, ",", people, ")"); // printing out the floor and number of people

            }
            writer.printf("\n"); // to go onto the next line to print off the next second
        }

        // need to code a break and some sort of input to go on to the next test file


    }


}
