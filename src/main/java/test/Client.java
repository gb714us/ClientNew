package test;

import javax.swing.JFrame;

public class Client {
	
	public static void main(String[] args){
		MainMenuFrame mf = new MainMenuFrame();
		mf.setSize(500, 500);
		mf.setResizable(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.pack();
		mf.setVisible(true);
	}
}
