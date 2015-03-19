package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

//import client.ClientConnector;

public class BoardFrame {
//	public static ClientConnector client = new ClientConnector("http://localhost:8080/");
	
	public BoardFrame(final String name, final int n, String username){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
				}
				// Do matchmaking
				String response = "";
				System.out.println(response);
				
				JFrame frame = new JFrame(name);
				frame.setSize(1200, 1200);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new BoardPanel(n));
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});
	}
}
