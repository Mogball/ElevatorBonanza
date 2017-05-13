package com.swaggydonuts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ultim on 2017-05-12.
 */
public class Controller {

    public Car[] cars;
    public Floor[] floors;
    public Main main;

    public Controller(int floors, int elevators, Main main) {
        this.main = main;
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
        int people = 0;
        int i = 0;
        List<Integer> floorNums = new ArrayList<>();
        for (Car car : cars) people += car.people.size();
        for (i = 0; i < floors.length; i++) if (floors[i].people.size() > 0) floorNums.add(i);
        main.PeopleInTransit.setText("People in transit: " + people);
        StringBuilder sb = new StringBuilder();
        main.FloorsWithPeople.setText("Floors with people: " + floorNums.toString());

        List<JLabel> Elevators = Arrays.asList(main.E1, main.E2, main.E3, main.E4, main.E5, main.E6, main.E7, main.E8,
                main.E9, main.E10);
        i = 0;
        for (Car car : cars) {
            Elevators.get(i).setText(car.floor + "");
            i += 1;
        }
        i = 0;
        for (Car car: cars){
            if(car.state == 0){
                Elevators.get(i).setIcon(main.StaticIcon);
            }
            else if(car.state == -1){
                Elevators.get(i).setIcon(main.LitDown);
            }
            else{
                Elevators.get(i).setIcon(main.UpLit);
            }
        }
    }

    public int peopleRemaining() {
        int people = 0;
        for (Floor floor : floors) people += floor.people.size();
        for (Car car : cars) people += car.peopleRemaining();
        return people;
    }

}
