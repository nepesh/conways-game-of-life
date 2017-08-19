package life.of.game.conway;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameSetup implements MouseListener, MouseMotionListener {

	private GameGui gamegui;

	public GameSetup(GameGui game) {
		this.gamegui = game;
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + ", "
				+ MouseInfo.getPointerInfo().getLocation().y + ")");
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		
		System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + ", "
				+ MouseInfo.getPointerInfo().getLocation().y + ")");
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
        
		System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + ", "
				+ MouseInfo.getPointerInfo().getLocation().y + ")");

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
