package com.swaggydonuts;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimerTask;
import java.util.Timer;

public class Main extends JFrame {

	JPanel content;
	JButton exampleButton;
	ImageIcon UpLit;
	ImageIcon LitDown;
	ImageIcon StaticIcon;
	JLabel E1;
	JLabel E2;
	JLabel E3;
	JLabel E4;
	JLabel E5;
	JLabel E6;
	JLabel E7;
	JLabel E8;
	JLabel E9;
	JLabel E10;
	JLabel PeopleInTransit;
	JLabel FloorsWithPeople;

	JLabel label;

	public Main() {
		super("Elevator Bonanza");

		content = (JPanel) getContentPane();
		content.setLayout(null);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1000, 600));

		exampleButton = new JButton("Stats");
		exampleButton.setLocation(0, 400);
		exampleButton.setSize(100, 50);
		exampleButton.setLayout(null);

		JLabel exampleLabel = new JLabel();
		exampleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		exampleLabel.setLocation(500, 200);
		exampleLabel.setSize(new Dimension(200, 200));
		exampleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		exampleLabel.setLayout(null);

		//Elevators begin
		UpLit = new ImageIcon(Main.class.getResource("UpLit.png"), "UpLit");
		LitDown = new ImageIcon(Main.class.getResource("LitDown.png"), "LitDown");
		StaticIcon = new ImageIcon(Main.class.getResource("static.png"), "Static");

		String GoingUp = new String("Going Up");

		E1 = new JLabel("0");
		E1.setBorder(BorderFactory.createLineBorder(Color.black));
		E1.setLocation(10, 100);
		E1.setSize(new Dimension(50, 100));
		E1.setFont(new Font("Arial", Font.BOLD, 24));
		E1.setLayout(null);
		E1.setIcon(UpLit);
		E1.setVerticalTextPosition(SwingConstants.BOTTOM);
		E1.setHorizontalTextPosition(SwingConstants.CENTER);

		GoingUp = "Going Down";

		E2 = new JLabel("0", SwingConstants.CENTER);
		E2.setBorder(BorderFactory.createLineBorder(Color.black));
		E2.setLocation(70, 100);
		E2.setSize(new Dimension(50, 100));
		E2.setFont(new Font("Arial", Font.BOLD, 24));
		E2.setLayout(null);
		E2.setIcon(LitDown);
		E2.setVerticalTextPosition(SwingConstants.BOTTOM);
		E2.setHorizontalTextPosition(SwingConstants.CENTER);

		E3 = new JLabel("0", SwingConstants.CENTER);
		E3.setBorder(BorderFactory.createLineBorder(Color.black));
		E3.setLocation(130, 100);
		E3.setSize(new Dimension(50, 100));
		E3.setFont(new Font("Arial", Font.BOLD, 24));
		E3.setLayout(null);
		E3.setIcon(StaticIcon);
		E3.setVerticalTextPosition(SwingConstants.BOTTOM);
		E3.setHorizontalTextPosition(SwingConstants.CENTER);

		E4 = new JLabel("0", SwingConstants.CENTER);
		E4.setBorder(BorderFactory.createLineBorder(Color.black));
		E4.setLocation(190, 100);
		E4.setSize(new Dimension(50, 100));
		E4.setFont(new Font("Arial", Font.BOLD, 24));
		E4.setLayout(null);
		E4.setIcon(StaticIcon);
		E4.setVerticalTextPosition(SwingConstants.BOTTOM);
		E4.setHorizontalTextPosition(SwingConstants.CENTER);

		E5 = new JLabel("0", SwingConstants.CENTER);
		E5.setBorder(BorderFactory.createLineBorder(Color.black));
		E5.setLocation(250, 100);
		E5.setSize(new Dimension(50, 100));
		E5.setFont(new Font("Arial", Font.BOLD, 24));
		E5.setLayout(null);
		E5.setIcon(StaticIcon);
		E5.setVerticalTextPosition(SwingConstants.BOTTOM);
		E5.setHorizontalTextPosition(SwingConstants.CENTER);

		E6 = new JLabel("0", SwingConstants.CENTER);
		E6.setBorder(BorderFactory.createLineBorder(Color.black));
		E6.setLocation(10, 260);
		E6.setSize(new Dimension(50, 100));
		E6.setFont(new Font("Arial", Font.BOLD, 24));
		E6.setLayout(null);
		E6.setIcon(StaticIcon);
		E6.setVerticalTextPosition(SwingConstants.BOTTOM);
		E6.setHorizontalTextPosition(SwingConstants.CENTER);

		E7 = new JLabel("0", SwingConstants.CENTER);
		E7.setBorder(BorderFactory.createLineBorder(Color.black));
		E7.setLocation(70, 260);
		E7.setSize(new Dimension(50, 100));
		E7.setFont(new Font("Arial", Font.BOLD, 24));
		E7.setLayout(null);
		E7.setIcon(StaticIcon);
		E7.setVerticalTextPosition(SwingConstants.BOTTOM);
		E7.setHorizontalTextPosition(SwingConstants.CENTER);

		E8 = new JLabel("0", SwingConstants.CENTER);
		E8.setBorder(BorderFactory.createLineBorder(Color.black));
		E8.setLocation(130, 260);
		E8.setSize(new Dimension(50, 100));
		E8.setFont(new Font("Arial", Font.BOLD, 24));
		E8.setLayout(null);
		E8.setIcon(StaticIcon);
		E8.setVerticalTextPosition(SwingConstants.BOTTOM);
		E8.setHorizontalTextPosition(SwingConstants.CENTER);

		E9 = new JLabel("0", SwingConstants.CENTER);
		E9.setBorder(BorderFactory.createLineBorder(Color.black));
		E9.setLocation(190, 260);
		E9.setSize(new Dimension(50, 100));
		E9.setFont(new Font("Arial", Font.BOLD, 24));
		E9.setLayout(null);
		E9.setIcon(StaticIcon);
		E9.setVerticalTextPosition(SwingConstants.BOTTOM);
		E9.setHorizontalTextPosition(SwingConstants.CENTER);

		E10 = new JLabel("0", SwingConstants.CENTER);
		E10.setBorder(BorderFactory.createLineBorder(Color.black));
		E10.setLocation(250, 260);
		E10.setSize(new Dimension(50, 100));
		E10.setFont(new Font("Arial", Font.BOLD, 24));
		E10.setLayout(null);
		E10.setIcon(StaticIcon);
		E10.setVerticalTextPosition(SwingConstants.BOTTOM);
		E10.setHorizontalTextPosition(SwingConstants.CENTER);
		//Elevators end
		JTextArea textArea = new JTextArea(5, 30);
		textArea.setText("HEllo");
		/*
		textArea.setSize(200, 600);
		textArea.setLocation( 600, 0);
		*/

		PeopleInTransit = new JLabel("People in transit: 0");
		PeopleInTransit.setBorder(BorderFactory.createLineBorder(Color.black));
		PeopleInTransit.setLocation(400, 100);
		PeopleInTransit.setSize(200, 50);
		PeopleInTransit.setLayout(null);

		FloorsWithPeople = new JLabel("Floors with people: None");
		FloorsWithPeople.setBorder(BorderFactory.createLineBorder(Color.black));
		FloorsWithPeople.setLocation(400, 150);
		FloorsWithPeople.setSize(400, 50);
		FloorsWithPeople.setLayout(null);

		JPanel p = new JPanel();
		p.setSize(600, 400);

		JScrollPane floors = new JScrollPane(p);

		floors.setLocation(600, 0);
		floors.setSize(200, 400);

		floors.setLayout(null);

		label = new JLabel();
		label.setLocation(0, 500);
		label.setSize(400, 50);
		label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		label.setLayout(null);
		content.add(label);
		//Event loop

		exampleButton.addActionListener(e -> exampleLabel.setText("You suck"));

		content.add(E1);
		content.add(E2);
		content.add(E3);
		content.add(E4);
		content.add(E5);
		content.add(E6);
		content.add(E7);
		content.add(E8);
		content.add(E9);
		content.add(E10);
		content.add(PeopleInTransit);
		content.add(FloorsWithPeople);

		pack();
		setVisible(true);

	}

	public static void print(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		boolean demo = true;
		if (!demo) {

			Main main = new Main();
			InputStream is = Main.class.getResourceAsStream("elevator3.json");
			try {
				Input input = Parser.parseInput(is);
				Controller controller = new Controller(input.floors, input.elevators, main);
				int i;
				List<OutputElement> outputs = new ArrayList<>();
				for (i = 0; i < Constant.ITERATIONS; i++) {
					outputs.add(new OutputElement(i, controller));
					Cheat.i = i;
					List<Event> events = input.events[i];
					for (Event event : events) controller.send(event);
					controller.update();
				}
				while (controller.peopleRemaining() != 0) {
					outputs.add(new OutputElement(i, controller));
					controller.update();
					i++;
					Cheat.i = i;
				}
				outputs.add(new OutputElement(i, controller));
				Output.writeOutput(outputs, "output3.txt");
				print(controller);
				print(Cheat.totalTime);
				print(Cheat.people);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Main main = new Main();
			InputStream is = Main.class.getResourceAsStream("elevator_practice3.json");
			try {
				Input input = Parser.parseInput(is);


				Controller controller = new Controller(input.floors, input.elevators, main);
				int i;
				List<OutputElement> outputs = new ArrayList<>();

				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					int i = 0;

					@Override
					public void run() {
						outputs.add(new OutputElement(i, controller));
						if (i < 1000) {
							List<Event> events = input.events[i];
							for (Event event : events) controller.send(event);
						}
						controller.update();
						i++;
						Cheat.i = i;

						if (i > 1000 && controller.peopleRemaining() == 0) {
							outputs.add(new OutputElement(i, controller));
							try {
								Output.writeOutput(outputs, "output.txt");
							} catch (IOException e) {
								e.printStackTrace();
							}
							print(controller);
							print(Cheat.totalTime);
							print(Cheat.people);

							cancel();
						}
					}
				}, 0, 25);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
