package test;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

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
			Messanger messenger = new Messanger();
			MatchedGame game = messenger.findGame(name, username);
			GUIBoard view;
			String [][] state = game.getGameState();
			System.out.println("Opened: " + name);
			if (name.equals("Othello"))
				view = new GUIBoard("Othello", 8, state);
			else if (name.equals("TicTacToe"))
				view = new GUIBoard("TicTacToe", 3, state);
			else if (name.equals("Battleship"))
				view = new GUIBoard("Battleship", 10, state);
			else
				System.out.println("NO AVAILABLE GAME");
			}
			
			//connect to server
		}

}


