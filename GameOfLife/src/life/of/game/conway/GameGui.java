package life.of.game.conway;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8819817510943091336L;
	
	boolean[][] gridState;
	String[][] gridcoord;
	int cellWidth, cellHeight;
	Graphics publicCell;

	public GameGui(boolean[][] newGrid) {

		gridState = newGrid;
		
	}

	public void setState(boolean[][] newGrid) {
		gridState = newGrid;
	}

	public int getCellWidth() {
		return this.cellWidth;
	}

	public int getCellHeight() {
		return this.cellHeight;
	}
	
	public String[][] getXYCoord() {
		return gridcoord;
		
	}

	public void paintCell(Graphics cell, int i, int j) {
        int initialx = (int) (i * cellWidth);
        int finalx = initialx + (int) (cellWidth);
        int initialy = (int) (j * cellHeight);
        int finaly = initialy+ (int) (cellHeight);
        
		cell.fillRect(initialx,initialy ,finalx , finaly);
		gridcoord[i][j]= initialx+","+finalx+","+initialy+","+finaly;
		System.out.println(gridcoord[i][j]);
	}

	@Override
	public void paintComponent(Graphics cell) {
		super.paintComponent(cell);
		
		cellWidth = this.getWidth() / gridState.length;
		cellHeight = this.getHeight() / gridState[0].length;
        gridcoord = new String[gridState.length][gridState[0].length];
		//fill the grid with colour
		for (int i = 0; i < gridState.length; i++) {
			for (int j = 0; j < gridState[0].length; j++) {
				if (gridState[i][j] == true) {
					cell.setColor(Color.blue);
					paintCell(cell, i, j);
				} else {
					cell.setColor(Color.white);
					paintCell(cell, i, j);
				}
			}
		}
		
		//draw the Grid Lines
		for (int i = 0; i < gridState.length + 1; i++) {

			cell.setColor(new Color(0, 0, 0));
			cell.drawLine((int) (i * cellWidth), 0, (int) (i * cellWidth), this.getHeight());
			cell.drawLine(0, (int) (i * cellHeight), this.getWidth(), (int) (i * cellHeight));
		}

	}
}
