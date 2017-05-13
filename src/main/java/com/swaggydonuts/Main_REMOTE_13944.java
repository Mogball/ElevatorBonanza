package com.swaggydonuts;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

public class Main {

	public static void print(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Elevator Bonanza");
		JPanel content = (JPanel) frame.getContentPane();
		content.setLayout(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000, 600));

		JButton exampleButton = new JButton("Stats");
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
		JLabel E1 = new JLabel("0", SwingConstants.CENTER);
		E1.setBorder(BorderFactory.createLineBorder(Color.black));
		E1.setLocation(10, 100);
		E1.setSize(new Dimension(50, 50));
		E1.setFont(new Font("Arial", Font.BOLD, 24));
		E1.setLayout(null);

		JLabel E2 = new JLabel("0", SwingConstants.CENTER);
		E2.setBorder(BorderFactory.createLineBorder(Color.black));
		E2.setLocation(70, 100);
		E2.setSize(new Dimension(50, 50));
		E2.setFont(new Font("Arial", Font.BOLD, 24));
		E2.setLayout(null);

		JLabel E3 = new JLabel("0", SwingConstants.CENTER);
		E3.setBorder(BorderFactory.createLineBorder(Color.black));
		E3.setLocation(130, 100);
		E3.setSize(new Dimension(50, 50));
		E3.setFont(new Font("Arial", Font.BOLD, 24));
		E3.setLayout(null);

		JLabel E4 = new JLabel("0", SwingConstants.CENTER);
		E4.setBorder(BorderFactory.createLineBorder(Color.black));
		E4.setLocation(190, 100);
		E4.setSize(new Dimension(50, 50));
		E4.setFont(new Font("Arial", Font.BOLD, 24));
		E4.setLayout(null);

		JLabel E5 = new JLabel("0", SwingConstants.CENTER);
		E5.setBorder(BorderFactory.createLineBorder(Color.black));
		E5.setLocation(250, 100);
		E5.setSize(new Dimension(50, 50));
		E5.setFont(new Font("Arial", Font.BOLD, 24));
		E5.setLayout(null);

		JLabel E6 = new JLabel("0", SwingConstants.CENTER);
		E6.setBorder(BorderFactory.createLineBorder(Color.black));
		E6.setLocation(10, 260);
		E6.setSize(new Dimension(50, 50));
		E6.setFont(new Font("Arial", Font.BOLD, 24));
		E6.setLayout(null);

		JLabel E7 = new JLabel("0", SwingConstants.CENTER);
		E7.setBorder(BorderFactory.createLineBorder(Color.black));
		E7.setLocation(70, 260);
		E7.setSize(new Dimension(50, 50));
		E7.setFont(new Font("Arial", Font.BOLD, 24));
		E7.setLayout(null);

		JLabel E8 = new JLabel("0", SwingConstants.CENTER);
		E8.setBorder(BorderFactory.createLineBorder(Color.black));
		E8.setLocation(130, 260);
		E8.setSize(new Dimension(50, 50));
		E8.setFont(new Font("Arial", Font.BOLD, 24));
		E8.setLayout(null);

		JLabel E9 = new JLabel("0", SwingConstants.CENTER);
		E9.setBorder(BorderFactory.createLineBorder(Color.black));
		E9.setLocation(190, 260);
		E9.setSize(new Dimension(50, 50));
		E9.setFont(new Font("Arial", Font.BOLD, 24));
		E9.setLayout(null);

		JLabel E10 = new JLabel("0", SwingConstants.CENTER);
		E10.setBorder(BorderFactory.createLineBorder(Color.black));
		E10.setLocation(250, 260);
		E10.setSize(new Dimension(50, 50));
		E10.setFont(new Font("Arial", Font.BOLD, 24));
		E10.setLayout(null);
		//Elevators end
		JTextArea textArea = new JTextArea(5, 30);
		textArea.setText("HEllo");
		/*
		textArea.setSize(200, 600);
		textArea.setLocation( 600, 0);
		*/

		JPanel p = new JPanel();
		p.setSize(600, 400);

		JScrollPane floors = new JScrollPane(p);

		floors.setLocation(600, 0);
		floors.setSize(200, 400);

		floors.setLayout(null);

		exampleButton.addActionListener(e -> exampleLabel.setText("You suck"));

		content.add(exampleButton);
		content.add(exampleLabel);
		content.add(floors);
		content.add(textArea);
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


		frame.pack();
		frame.setVisible(true);

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
