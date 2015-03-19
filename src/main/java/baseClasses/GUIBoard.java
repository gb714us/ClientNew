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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import battleship.BattleshipGame;
import battleship.BattleshipLogic;
import battleship.Console;
import othello.OthelloGame;
import tictactoe.TicTacToeGame;

public class GUIBoard {
	
	private String title;
	private int size;
	private Messanger messanger;

    public GUIBoard(String name,int number) {
    	title = name;
    	size = number;
    	messanger = new Messanger();
        EventQueue.invokeLater(new Runnable() {

			@Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame(title);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                ScorePanel score = new ScorePanel();
                frame.add(new BoardPanel(size));
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
		
		public BoardPanel(int size) {
			Player p1 = new Player("Player1");
			Player p2 = new Player("Player2");
//			Game ttt = new TicTacToeGame(p1,p2);
			Game ttt = null;
			if (title.equals("Othello"))
				ttt = new OthelloGame(p1,p2);
			else if (title.equals("Tic Tac Toe"))
				ttt = new TicTacToeGame(p1,p2);
			else if (title.equals("Tic Tac Toe"))
				ttt = new BattleshipGame(p1,p2);
			else
				System.out.println("NO AVAILABLE GAME");
			
//			Game ttt = new BattleshipGame(p1,p2);
			ttt.initializeGame();
			setSize(2000,2000);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            for(int i =0;i<2;i++)
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    CellPanel cellPanel = new CellPanel(row, col,ttt,this);
                    
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
        private Game ttgame;
        private State state;
        private JPanel panel;
        

        public CellPanel(int row, int col,Game game,JPanel pane) {
        	panel = pane;
        	r = row;
        	c = col;
        	ttgame = game;
        	state = ttgame.getState();
        	final Console con = new Console();

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
					ttgame.getLogic().placePiece(r,c);
					if(ttgame instanceof BattleshipGame)
						con.print((BattleshipLogic) ttgame.getLogic());
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
        	state = ttgame.getState();
        	if(state.getPiece(r, c)!=null)
        		if(state.getPiece(r, c).getType()=="X"){
        			g.setColor(Color.black);
        			int thickness = 8;
        			for(int i = 0; i < thickness; i++) {
        			    g.drawLine( 4-i, 0, getWidth()+4-i, getHeight());
        			    g.drawLine( getHeight()+4-i, 0, 4-i, getWidth());

        			}
        		}
        		else if(state.getPiece(r, c).getType()=="O"){
        			g.setColor(Color.black);
        			g.fillOval(0,0,getWidth(),getHeight());
        			g.setColor(getBackground());
        			g.fillOval(5,5,getWidth()-10,getHeight()-10);

        		}
        		else if(state.getPiece(r, c).getType()=="W"){
        			g.setColor(Color.white);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}
        		else if(state.getPiece(r, c).getType()=="B"){
        			g.setColor(Color.black);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}
        		else if(state.getPiece(r, c).getType()=="dead"){
        			g.setColor(Color.black);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}
        		else if(state.getPiece(r, c).getType()=="hit"){
        			g.setColor(Color.red);
        			g.fillOval(0,0,getWidth(),getHeight());
        		}

        	



            
        }
    }
    }
        
        
   
        
    
}
    
    
