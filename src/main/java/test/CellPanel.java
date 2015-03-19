package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class CellPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Color defaultBackground;
	private int row;
	private int col;

    public CellPanel(int r, int c) {
    	row = r;
    	col = c;
    	
    	addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				defaultBackground = getBackground();
//				add(new JLabel("X",JLabel.CENTER),BoardLayout.Center);
                setBackground(Color.BLUE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
                System.out.println(row + ", " + col);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
    	}	
    		
 
        /*addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                defaultBackground = getBackground();
                setBackground(Color.BLUE);
                System.out.println(row + ", " + col);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }
        });*/


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(75, 75);
    }
}
