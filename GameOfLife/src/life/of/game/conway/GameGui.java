package life.of.game.conway;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameGui extends JPanel {

	boolean[][] gridState;

	public GameGui(boolean[][] newGrid) {

		gridState = newGrid;
	}

	public void setState(boolean[][] newGrid) {
		gridState = newGrid;
	}
	
	

	@Override
	public void paintComponent(Graphics cell) {
		super.paintComponent(cell);

		int cellWidth = this.getWidth() / gridState.length;
		int cellHeight = this.getHeight() / gridState[0].length;
		
		for (int i = 0; i < gridState.length; i++) {
			for (int j = 0; j < gridState[0].length; j++) {
				if (gridState[i][j] == true) {
					cell.setColor(Color.green);
					cell.fillRect((int) (i * cellWidth), (int) (j * cellHeight), (int) (cellWidth),
							(int) (cellHeight));
				}else {
					cell.setColor(Color.white);
					cell.fillRect((int) (i * cellWidth), (int) (j * cellHeight), (int) (cellWidth),
							(int) (cellHeight));
				}
			}
		}
		for (int i = 0; i < gridState.length + 1; i++) {

			cell.setColor(new Color(0, 0, 0));
			cell.drawLine((int) (i * cellWidth), 0, (int) (i * cellWidth), this.getHeight());
			cell.drawLine(0, (int) (i * cellHeight), this.getWidth(), (int) (i * cellHeight));
		}

	}
}
