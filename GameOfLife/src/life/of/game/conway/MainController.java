package life.of.game.conway;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
	JLabel xlabel = new JLabel("X", SwingConstants.RIGHT);
	JLabel ylabel = new JLabel("Y", SwingConstants.RIGHT);
	JLabel description = new JLabel("Please enter Grid Size:");
	JLabel countLabel = new JLabel("Step Counter:");
	
	
	JLabel Count = new JLabel();
	JTextField xcoord = new JTextField();
	JTextField ycoord = new JTextField();
	int xcoordinate = 10;
	int ycoordinate = 10;
	int screenshotcount=1;
	Timer gameStart;
	int gameCount = 0;
	GameGui panel;
	GameSetup mouseMovement;
	Container buttonContainer = new Container();
	Container topContainer = new Container();

	public MainController() {
		currentState = new boolean[xcoordinate][ycoordinate];

		// create Game Front End
		panel = new GameGui(currentState);

		// Initialize the mouse events
		mouseMovement = new GameSetup(panel);
		panel.addMouseListener(mouseMovement);
		panel.addMouseMotionListener(mouseMovement);
		
		//Initial State of the Game
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
		
		//Set Layouts
		buttonContainer.setLayout(new FlowLayout());
		topContainer.setLayout(new FlowLayout());
		
		//Setup Bottom COntainer
		buttonContainer.add(Next);
		buttonContainer.add(Stop);
		buttonContainer.add(Reset);
		buttonContainer.add(Step);
		buttonContainer.add(Clear);
		buttonContainer.add(ScreenShot);
		
		//setup Top Container
		xlabel.setPreferredSize(new Dimension(20,20));
		ylabel.setPreferredSize(new Dimension(20,20));
		xcoord.setPreferredSize(new Dimension(80,20));
		ycoord.setPreferredSize(new Dimension(80,20));
		topContainer.add(description);
		topContainer.add(xlabel);
		topContainer.add(xcoord);
		topContainer.add(ylabel);
		topContainer.add(ycoord);
		topContainer.add(Set);
		topContainer.add(countLabel);
		Count.setPreferredSize(new Dimension(40,20));
		topContainer.add(Count);
		

		frame.add(buttonContainer, BorderLayout.SOUTH);
		frame.add(topContainer, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Action Listeners for all BUttons
		
		//Take Screenshot
		ScreenShot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				       
				       BufferedImage bufImg = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);  
				       panel.paint(bufImg.createGraphics());  
				       File imageFile = new File("images/Game_"+screenshotcount+".jpeg");  
				       screenshotcount++;
				    
				        try {
							imageFile.createNewFile();
							 ImageIO.write(bufImg, "jpeg", imageFile);  
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
				       
				  
				 
			}
		});
		
		//Perform Step by Step Iteration of the Game
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
		
		//Start a Swing Timer Thread
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

	//Check if the Game has reached a stable state
	void checkStable() {
		if (Arrays.deepEquals(currentState, lastState)) {
			JOptionPane.showMessageDialog(frame, "The Game Has reached a Stable State in "+gameCount+" steps.");
			gameStart.stop();
		}
	}

	void drawFrame() {
		game = new GameRules();
		mouseMovement.setGameRules(game);
		game.setCurrentStateListSize(xcoordinate, ycoordinate);
		game.setpossibleFutureStateListSize(xcoordinate, ycoordinate);
		
		//check if the mouse has drawn new states
		if (mouseMovement.start) {
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
