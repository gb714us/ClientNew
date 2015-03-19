 package test;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class GUIBoard {
	
	private String title;
	private int size;
	private Messanger messanger;
	private String [][] state;
	

    public GUIBoard(String name,int number, Messanger mes) {
    	title = name;
    	size = number;
    	this.messanger = mes;
//    	state = newState;
        EventQueue.invokeLater(new Runnable() {

			@Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame(title);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                ScorePanel score = new ScorePanel();
                frame.add(new BoardPanel(size, messanger));
                frame.add(score);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        
        
    }
    
    
    public class ScorePanel extends JPanel{
    	/**
		 * 
		 */
    	private int score = 0;
    	private String turn = null;
    	
		private static final long serialVersionUID = 1L;
		public ScorePanel()
    	{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	}
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
        @Override
        public void paintComponent(Graphics g)
        {
        	super.paintComponent(g);
        	setBackground(Color.white);
    		add(new JLabel("Score: "+score));
    		add(new JLabel("Turn: "+turn));
        }
        
        public void setScore(int s)
        {
        	score = s;
        }
        
        public void setTurn(String s)
        {
        	turn = s;
        }
    }
    
 

    public class BoardPanel extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String [][] state;
		
		public BoardPanel(int size, Messanger messanger) {
			setSize(2000,2000);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    CellPanel cellPanel = new CellPanel(row, col,messanger,this);
                    
                    Border border = null;
                    if (row < size-1) {
                        if (col < size-1) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < size-1) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    cellPanel.setBorder(border);
                    add(cellPanel, gbc);



                }
                
            }
    
		
		
    }

    public class CellPanel extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
        private int r;
        private int c;
        private String[][] state;
        private JPanel panel;
        private final Messanger messanger;

        public CellPanel(int row, int col,Messanger messange,JPanel pane) {
        	panel = pane;
        	r = row;
        	c = col;
        	this.messanger = messange;
        	state = messanger.getCurrentState();

            addMouseListener(new MouseListener() {

				@Override
                public void mouseEntered(MouseEvent e) {
//                    System.out.println(r+", "+c);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
					messanger.performMove(r, c);
//					if(ttgame instanceof BattleshipGame)
//						con.print((BattleshipLogic) ttgame.getLogic());
					panel.repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}
            });
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(75, 75);
        }
        @Override
        public void paintComponent(Graphics g)
        {
//        	if(state[r][c] == null)
        		if(state[r][c].equals("X")){
        			g.setColor(Color.black);
        			int thickness = 8;
        			for(int i = 0; i < thickness; i++) {
        			    g.drawLine( 4-i, 0, getWidth()+4-i, getHeight());
        			    g.drawLine( getHeight()+4-i, 0, 4-i, getWidth());

        			}
        		}
        		else if(state[r][c].equals("O")){
        			g.setColor(Color.black);
        			g.fillOval(0,0,getWidth(),getHeight());
        			g.setColor(getBackground());
        			g.fillOval(5,5,getWidth()-10,getHeight()-10);

        		}
        		else if(state[r][c].equals("W")){
        			g.setColor(Color.white);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}
        		else if(state[r][c].equals("B")){
        			g.setColor(Color.black);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}
        		else if(state[r][c].equals("dead")){
        			g.setColor(Color.black);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}
        		else if(state[r][c].equals("hit")){
        			g.setColor(Color.red);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}

        	



            
        }
    }
    }
        
        
   
        
    
}
    
    
