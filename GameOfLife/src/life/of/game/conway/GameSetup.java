package life.of.game.conway;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameSetup implements MouseListener, MouseMotionListener {

	private GameGui gamegui;
	GameRules gamerules;
	Frame gameframe;
	boolean[][] drawnstates;
	public boolean start;
	int gridheight;
	public GameSetup(GameGui game) {
		this.gamegui = game;
		
	}
	public void setGameRules(GameRules rules) {
		this.gamerules = rules;
		
	}
	
	public void setDrawnStates(int x, int y) {
		this.drawnstates = new boolean[x][y];
		gridheight = y;
	}
	
	public boolean[][] getStates(){
		return drawnstates;
		
	}
	public boolean getStateValue(int x , int y){
		return drawnstates[x][y];
		
	}

	public void setState(int x, int y, boolean state) {
		drawnstates[x][y] = state;
	}
	
	
	
	public void mousePressed(MouseEvent e) {
		String[][] actualCoord;
		int width = gamegui.getCellWidth();
		int height = gamegui.getCellHeight();
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y =  MouseInfo.getPointerInfo().getLocation().y;
		int xcoord = 0 ;
		int ycoord =0;
		
		
		System.out.println("(" +x+ ", "+(y-40)+ ")");
		System.out.println("(" +width+ ", "+height + ")");
		
		
		actualCoord = gamegui.getXYCoord();
		String[] xy;
		for (int i = 0; i < gamegui.gridState.length; i++) {
			for (int j = 0; j < gamegui.gridState[0].length; j++) {
				xy = actualCoord[i][j].split(",");
				
				if ( x >=Integer.valueOf(xy[0]) && x <=Integer.valueOf(xy[1]) ) {
					xcoord = Integer.valueOf(xy[0])/width;
				}
				if ( y-40>=Integer.valueOf(xy[2]) && y-40<=Integer.valueOf(xy[3])) {
					System.out.println(xy[2]);
					
					ycoord = Integer.valueOf(xy[2])/height;
				}
			}
		}
		System.out.println("(gridx - " + xcoord + ", gridy - "+ycoord + ")" );
		gamerules.setCurrentState(xcoord, ycoord, true);
		setState(xcoord, ycoord, true);
		Graphics graphic = e.getComponent().getGraphics();
		graphic.setColor(Color.BLUE);
		
		graphic.fillRect(xcoord*width, ycoord*height, width, height);
		start = true;
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		        
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
        
		String[][] actualCoord;
		int width = gamegui.getCellWidth();
		int height = gamegui.getCellHeight();
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y =  MouseInfo.getPointerInfo().getLocation().y;
		int xcoord = (int) Math.floor(x/width);
		int ycoord = (int) Math.ceil(y/height)- (gridheight/10);
		
		
		System.out.println("(" +x+ ", "+y + ")");
		System.out.println("(" +width+ ", "+height + ")");
		System.out.println("(gridx - " + xcoord + ", gridy - "+ycoord + ")" );
		
		actualCoord = gamegui.getXYCoord();
		String[] xy;
		for (int i = 0; i < gamegui.gridState.length; i++) {
			for (int j = 0; j < gamegui.gridState[0].length; j++) {
				xy = actualCoord[i][j].split(",");
				
				if (Integer.valueOf(xy[0]) >= x && Integer.valueOf(xy[1]) <= x ) {
					xcoord = Integer.valueOf(xy[0]);
				}
				if (Integer.valueOf(xy[2]) >= y && Integer.valueOf(xy[3]) <= y ) {
					ycoord = Integer.valueOf(xy[2]);
				}
			}
		}
		gamerules.setCurrentState(xcoord, ycoord, true);
		setState(xcoord, ycoord, true);
		Graphics graphic = e.getComponent().getGraphics();
		graphic.setColor(Color.BLUE);
		
		graphic.fillRect(xcoord*width, ycoord*height, width, height);
		start = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
