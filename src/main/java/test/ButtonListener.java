package test;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import baseClasses.GUIBoard;
import test.SqlCommand;

public class ButtonListener implements ActionListener {

	private String name;
	private JTextField usernameField;
	public static String username;
	private int number;

	public ButtonListener(String n, int x){
		name = n;
		number = x;
	}
	
	public ButtonListener(String action, JTextField usernameField) {
		this.name = action;
		this.usernameField = usernameField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (name.equals("Login")) {
			username = usernameField.getText();
			SqlCommand.createNewUser(username);
		} 
		else {
			
			GUIBoard view;
			System.out.println("Opened: " + name);
			if (name.equals("Othello"))
				view = new GUIBoard("Othello", 8);
			else if (name.equals("Tic Tac Toe"))
				view = new GUIBoard("Tic Tac Toe", 3);
			else if (name.equals("Battleship"))
				view = new GUIBoard("Battleship", 10);
			else
				System.out.println("NO AVAILABLE GAME");
			}
			
			//connect to server
		}

}


