package life.of.game.conway;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MainController extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	static JFrame frame = new JFrame("Game of Life");
	int[][] neighbourList = new int[8][2];
	boolean[][] futureState, currentState;

	JButton Next = new JButton("Start");
	JButton Set = new JButton("Set");
	JButton Stop = new JButton("Stop");
	JLabel xlabel = new JLabel("X");
	JLabel ylabel = new JLabel("Y");
	JTextField xcoord = new JTextField();
	JTextField ycoord = new JTextField();
	int xcoordinate = 10;
	int ycoordinate = 10;
	Timer gameStart;
	GameGui panel;

	Container buttonContainer = new Container();

	public MainController() {
		currentState = new boolean[xcoordinate][ycoordinate];
		Random randomGenerator = new Random();
		for (int idx = 1; idx <= 20; ++idx) {
			int randomIntx = randomGenerator.nextInt(xcoordinate);
			int randomInty = randomGenerator.nextInt(ycoordinate);
			currentState[randomIntx][randomInty] = true;
		}
		panel = new GameGui(currentState);
		frame.setSize(600, 660);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		buttonContainer.setLayout(new GridLayout(1, 7));
		buttonContainer.add(Next);
		buttonContainer.add(Stop);
		buttonContainer.add(xlabel);
		buttonContainer.add(xcoord);
		buttonContainer.add(ylabel);
		buttonContainer.add(ycoord);
		buttonContainer.add(Set);
		frame.add(buttonContainer, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				simulateGame();

			}
		});
		Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.stop();

			}
		});
		Set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				xcoordinate = Integer.parseInt(xcoord.getText());
				ycoordinate = Integer.parseInt(ycoord.getText());
				currentState = new boolean[xcoordinate][ycoordinate];
				Random randomGenerator = new Random();
				for (int idx = 1; idx <= 20; ++idx) {
					int randomIntx = randomGenerator.nextInt(xcoordinate);
					int randomInty = randomGenerator.nextInt(ycoordinate);
					currentState[randomIntx][randomInty] = true;
				}
				panel.setState(currentState);
				frame.repaint();
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainController();
			}
		});
	}

	void simulateGame() {
		int timerDelay = 500;
		gameStart = new Timer(timerDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFrame();
				panel.setState(currentState);
				frame.repaint();
			}
		});
		gameStart.start();
	}

	void drawFrame() {

		GameRules game = new GameRules();
		game.setCurrentStateListSize(xcoordinate, ycoordinate);
		game.setpossibleFutureStateListSize(xcoordinate, ycoordinate);
		game.setCurrentStateList(currentState);
		for (int x = 0; x < xcoordinate; x++) {
			for (int y = 0; y < ycoordinate; y++) {
				neighbourList = game.calculateNeighbour(x, y, xcoordinate - 1, ycoordinate - 1);
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
		// System.out.println("future"+Arrays.deepToString(futureState));
		currentState = futureState;
		// System.out.println("current"+Arrays.deepToString(currentState));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
