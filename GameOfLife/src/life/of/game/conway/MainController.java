package life.of.game.conway;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Array;
import java.util.Arrays;
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
	JButton Reset = new JButton("Reset");
	JLabel xlabel = new JLabel("X");
	JLabel ylabel = new JLabel("Y");
	JLabel Count = new JLabel();
	JTextField xcoord = new JTextField();
	JTextField ycoord = new JTextField();
	int xcoordinate = 10;
	int ycoordinate = 10;
	Timer gameStart;
	int gameCount = 0;
	GameGui panel;

	Container buttonContainer = new Container();

	public MainController() {
		currentState = new boolean[xcoordinate][ycoordinate];
		Random randomGenerator = new Random();
		int totalRandom = (xcoordinate * ycoordinate) / 2;
		for (int idx = 1; idx <= totalRandom; ++idx) {
			int randomIntx = randomGenerator.nextInt(xcoordinate);
			int randomInty = randomGenerator.nextInt(ycoordinate);
			currentState[randomIntx][randomInty] = true;
		}
		panel = new GameGui(currentState);
		frame.setSize(600, 660);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		buttonContainer.setLayout(new GridLayout(1, 9));
		buttonContainer.add(Next);
		buttonContainer.add(Stop);
		buttonContainer.add(Reset);
		buttonContainer.add(xlabel);
		buttonContainer.add(xcoord);
		buttonContainer.add(ylabel);
		buttonContainer.add(ycoord);
		buttonContainer.add(Set);
		buttonContainer.add(Count);
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
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Random randomGenerator = new Random();
				int totalRandom = (xcoordinate * ycoordinate) / 2;
				for (int idx = 1; idx <= totalRandom; ++idx) {
					int randomIntx = randomGenerator.nextInt(xcoordinate);
					int randomInty = randomGenerator.nextInt(ycoordinate);
					currentState[randomIntx][randomInty] = true;
				}
				gameCount = 0;
				panel.setState(currentState);
				frame.repaint();
			}
		});
		Set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				xcoordinate = Integer.parseInt(xcoord.getText());
				ycoordinate = Integer.parseInt(ycoord.getText());
				int totalRandom = (xcoordinate * ycoordinate) / 2;
				currentState = new boolean[xcoordinate][ycoordinate];
				Random randomGenerator = new Random();
				for (int idx = 1; idx <= totalRandom; ++idx) {
					int randomIntx = randomGenerator.nextInt(xcoordinate);
					int randomInty = randomGenerator.nextInt(ycoordinate);
					currentState[randomIntx][randomInty] = true;
				}
				gameCount = 0;
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
				gameCount++;
				Count.setText(Integer.toString(gameCount));
				
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
