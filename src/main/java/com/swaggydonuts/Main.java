package com.swaggydonuts;

import com.swaggydonuts.stuff.Input;
import com.swaggydonuts.stuff.Parser;

import javax.swing.*;
import java.awt.*;
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
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1600, 800));

		JButton exampleButton = new JButton("Stats");
		exampleButton.setLocation(0, 700);
		exampleButton.setSize(100, 50);
		exampleButton.setLayout(null);

		JLabel exampleLabel = new JLabel();
		exampleLabel.setLocation(500, 200);
		exampleLabel.setSize(200, 200);
		exampleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		exampleLabel.setLayout(null);

		exampleButton.addActionListener(e -> exampleLabel.setText("You suck"));

		content.add(exampleButton);
		content.add(exampleLabel);

		frame.pack();
		frame.setVisible(true);

		InputStream is = Main.class.getClassLoader().getResourceAsStream("elevator_practice1.json");
		try {
			Input input = Parser.parseInput(is);
			print(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
