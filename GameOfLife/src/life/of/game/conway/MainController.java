package life.of.game.conway;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainController extends JFrame implements MouseListener{

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame frame = new JFrame("Game of Life");
 	 int[][] neighbourList = new int[8][2];
	 boolean[][] futureState = new boolean[10][10];
     boolean[][] currentState = new boolean[][]{
		  { false, false, false, false, false, false, false, false, false, false },
		  { false, false, false, false, false, false, false, false, false, false },
		  { false, false, false, true, false, true, false, false, false, false  },
		  { false, false, false, false, true, true, false, false, false, false  },
		  { false, false, false, false, true, false, false, false, false, false  },
		  { false, false, false, false, false, false, false, false, false, false },
		  { false, false, false, false, false, false, false, false, false, false },
		  { false, false, false, false, false, false, false, false, false, false },
		  { false, false, false, false, false, false, false, false, false, false },
		  { false, false, false, false, false, false, false, false, false, false }
		};
		 JButton Next = new JButton("Next");
		 JButton Animate = new JButton("Animate");
		 JButton Stop = new JButton("Stop");

		GameGui panel = new GameGui(currentState);

 	    Container north = new Container();
 	  
 		
    public MainController() {
    	   
        frame.setSize(600, 660);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        north.setLayout(new GridLayout(1,3));
        north.add(Next);
        north.add(Animate);
        north.add(Stop);
        frame.add(north, BorderLayout.NORTH);
        panel.addMouseListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
	
	public static void main(String[] args) {
		
		new MainController();
		
		
	}
	
	void drawFrame() {
		GameRules game = new GameRules();
		game.setCurrentStateList(currentState);
		for (int x=0;x<=9;x++) {
			for (int y=0;y<=9;y++) {
				neighbourList = game.calculateNeighbour(x, y);
				game.countLiveCells(neighbourList);
				
				if (game.getCellsCurrentState(x, y)) {
					if (game.gameRuleOne() && game.gameRuleTwoAndThree()) {
						game.setPossibleFutureState(x, y, true);
					} else {
						game.setPossibleFutureState(x, y, false);
					}
				} else {
					if (game.gameRuleFour()) {
						game.setPossibleFutureState(x, y, true);
					} else {
						game.setPossibleFutureState(x, y, false);
					}
				}
			}
		}

		futureState = game.getPossibleFutureList();
		System.out.println("future"+Arrays.deepToString(futureState));
		currentState = futureState;
		System.out.println("current"+Arrays.deepToString(currentState));
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	 	drawFrame();
		panel.setState(currentState);
		frame.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
