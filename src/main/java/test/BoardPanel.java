package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class BoardPanel extends JPanel {

    public BoardPanel(int n) {
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                CellPanel cellPanel = new CellPanel(row, col);
                Border border = null;
                if (row < n-1) {
                    if (col < n-1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < n-1) {
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
}
