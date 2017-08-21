package life.of.game.conway;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MainController extends JFrame {

	private static final long serialVersionUID = 1L;
	static JFrame frame = new JFrame("Game of Life");
	int[][] neighbourList = new int[8][2];
	boolean[][] futureState, currentState, lastState;
	GameRules game;

	// Define Buttons
	JButton Next = new JButton("Start");
	JButton Set = new JButton("Set");
	JButton Stop = new JButton("Stop");
	JButton Reset = new JButton("Random");
	JButton Clear = new JButton("Clear");
	JButton Step = new JButton("Step");
	JButton ScreenShot = new JButton("ScreenShot");

	// Labels
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
	GameSetup mouseMovement;
	Container buttonContainer = new Container();

	public MainController() {
		currentState = new boolean[xcoordinate][ycoordinate];

		// create Game Front End
		panel = new GameGui(currentState);

		// Initialize the mouse events
		mouseMovement = new GameSetup(panel);
		panel.addMouseListener(mouseMovement);
		panel.addMouseMotionListener(mouseMovement);
		game = new GameRules();
		game.setCurrentStateListSize(xcoordinate, ycoordinate);
		game.setpossibleFutureStateListSize(xcoordinate, ycoordinate);
		currentState = new boolean[][] { { false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false } };
		game.setCurrentStateList(currentState);
		mouseMovement.setGameRules(game);
		mouseMovement.setDrawnStates(xcoordinate, ycoordinate);
		// set the Front End
		frame.setSize(600, 600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		buttonContainer.setLayout(new GridLayout(1, 12));
		buttonContainer.add(Next);
		buttonContainer.add(Stop);
		buttonContainer.add(Reset);
		buttonContainer.add(Step);
		buttonContainer.add(Clear);
		buttonContainer.add(xlabel);
		buttonContainer.add(xcoord);
		buttonContainer.add(ylabel);
		buttonContainer.add(ycoord);
		buttonContainer.add(Set);
		buttonContainer.add(Count);
		buttonContainer.add(ScreenShot);
		frame.add(buttonContainer, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ScreenShot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			 
				       BufferedImage bufImg = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);  
				       panel.paint(bufImg.createGraphics());  
				       File imageFile = new File("images/hello.jpeg");  
				    
				        try {
							imageFile.createNewFile();
							 ImageIO.write(bufImg, "jpeg", imageFile);  
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
				       
				  
				 
			}
		});
		Step.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				drawFrame();
				panel.setState(currentState);
				frame.repaint();
				gameCount++;
				Count.setText(Integer.toString(gameCount));
				checkStable();

			}
		});

		// Clear The GUI
		Clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Reset the current States
				for (int x = 0; x < xcoordinate; x++) {
					for (int y = 0; y < ycoordinate; y++) {
						currentState[x][y] = false;
					}
				}
				for (int x = 0; x < xcoordinate; x++) {
					for (int y = 0; y < ycoordinate; y++) {
						mouseMovement.setState(x, y, false);
					}
				}

				gameCount = 0;
				panel.setState(currentState);
				frame.repaint();
			}
		});

		// Start the simulation
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulateGame();

			}
		});

		// Stop the simulation
		Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.stop();

			}
		});

		// Reset the current Gui and generate new Ones
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setCurrentStateList(currentState);
				mouseMovement.setGameRules(game);
				mouseMovement.setDrawnStates(xcoordinate, ycoordinate);
				Random randomGenerator = new Random();
				// Reset the current States
				for (int x = 0; x < xcoordinate; x++) {
					for (int y = 0; y < ycoordinate; y++) {
						currentState[x][y] = false;
					}
				}

				// Generate the random states
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

		// Set the grid Size by taking input from user
		Set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				xcoordinate = Integer.parseInt(xcoord.getText());
				ycoordinate = Integer.parseInt(ycoord.getText());
				currentState = new boolean[xcoordinate][ycoordinate];

				game.setCurrentStateList(currentState);
				mouseMovement.setGameRules(game);
				mouseMovement.setDrawnStates(xcoordinate, ycoordinate);

				for (int x = 0; x < xcoordinate; x++) {
					for (int y = 0; y < ycoordinate; y++) {
						currentState[x][y] = false;
					}
				}
				for (int x = 0; x < xcoordinate; x++) {
					for (int y = 0; y < ycoordinate; y++) {
						mouseMovement.setState(x, y, false);
					}
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
				checkStable();
			}
		});
		gameStart.start();
	}

	void checkStable() {
		if (Arrays.equals(currentState, lastState)) {
			gameStart.stop();
		}
	}

	void drawFrame() {
		game = new GameRules();
		mouseMovement.setGameRules(game);
		game.setCurrentStateListSize(xcoordinate, ycoordinate);
		game.setpossibleFutureStateListSize(xcoordinate, ycoordinate);
		if (mouseMovement.start) {
			// currentState = mouseMovement.getStates();
			for (int x = 0; x < xcoordinate; x++) {
				for (int y = 0; y < ycoordinate; y++) {
					if (mouseMovement.getStateValue(x, y)) {
						currentState[x][y] = mouseMovement.getStateValue(x, y);
					}
					
				}
			}
			mouseMovement.start = false;
		}
		game.setCurrentStateList(currentState);

		// Generate the future state by applying the rules
		for (int x = 0; x < xcoordinate; x++) {
			for (int y = 0; y < ycoordinate; y++) {
				neighbourList = game.calculateNeighbour(x, y, xcoordinate - 1, ycoordinate - 1);
				game.countLiveCells(neighbourList);

				game.calculateFutureCellState(game, x, y);
			}
		}

		futureState = game.getPossibleFutureList();
		lastState = currentState;
		currentState = futureState;

	}

}
