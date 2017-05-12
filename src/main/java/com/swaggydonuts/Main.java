package com.swaggydonuts;

import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Elevator Bonanza");
		JPanel content = (JPanel) frame.getContentPane();
		content.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1600, 800));

		JButton exampleButton = new JButton("Click me");
		exampleButton.setLocation(200, 200);
		exampleButton.setSize(200, 50);
		exampleButton.setLayout(null);

		JLabel exampleLabel = new JLabel();
		exampleLabel.setLocation(500, 200);
		exampleLabel.setSize(200, 200);
		exampleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		exampleLabel.setLayout(null);

		exampleButton.addActionListener(e -> {
			exampleLabel.setText("You suck");
		});

		content.add(exampleButton);
		content.add(exampleLabel);

		frame.pack();
		frame.setVisible(true);
	}
}
