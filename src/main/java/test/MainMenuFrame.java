package test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class MainMenuFrame extends JFrame {

	private JButton b_Button;
	private JButton t_Button;
	private JButton o_Button;
	private JTextField userID;
	private JButton loginButton;
	
	public MainMenuFrame() {
		super("Group 2 Final Project");
		// Get uid
		// int uid = client.getUID();
		
		this.setLayout(new FlowLayout());
		
		JPanel buttonPanel = new JPanel();
		JPanel loginPanel = new JPanel();
		JPanel textPanel = new JPanel();
		userID = new JTextField(10);
		
		b_Button = new JButton("Battlehhip");
		b_Button.addActionListener(new ButtonListener("Battleship", 10));
		
		t_Button = new JButton("TicTacToe");
		t_Button.addActionListener(new ButtonListener("TicTacToe", 3));
		
		o_Button = new JButton("Othello");
		o_Button.addActionListener(new ButtonListener("Othello", 10));
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ButtonListener("Login", userID));
		
		
		buttonPanel.add(b_Button);
		buttonPanel.add(t_Button);
		buttonPanel.add(o_Button);
		loginPanel.add(userID);
		loginPanel.add(loginButton);
		JLabel textLabel = new JLabel("Click on the game you want to play!");
		textPanel.add(textLabel);
		
		this.add(buttonPanel);
		this.add(loginPanel);
		this.add(textPanel);
	
		
		}
}
