package concurrente;

import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import common.Cell;
import common.GameData;
import common.Position;
import common.ScreenData;
import common.Sprite;
import common.Stats;
import view.BoardView;

/**
 * 
 * Clase que representa la interfaz gráfica del juego.
 * 
 * Remake of a Connect-4 strategy game based on version authored by Andrew Zhao
 * 
 * @author RCSD 
 * @version 2021
 */
public class GUI extends JFrame implements Runnable {

	/**
	 * Version 1 for this game class
	 */
	private static final long serialVersionUID = 1L;

	private static final long PERIOD_IN_MS = 40;

	/**
	 * Represents the view or what the user sees.
	 */
	private final BoardView view;
	private final GameControl game;

	// Data to be drawn in the view
	private Stats statsData;
	private List<Cell> mazeData;
	private Position[] ghostsData;
	private Sprite pacmanData = null;
	private Sprite ghostPlayerData = null;
	
	private Thread guiThread;          // Thread to control the GUI

	// Constructor
	public GUI (GameControl game, HumanController player) {
		view = new BoardView();
		this.game = game;

		final int frameHeight = GameData.PLAY_SCREEN_SIZE + GameData.BLOCK_SIZE; // Allow space for score and remaining lives
		final int frameWidth = GameData.PLAY_SCREEN_SIZE;
		//Setup Frame
		this.getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.setTitle("Simple Pacman");
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 

		this.addWindowListener(new CloseListener());
		this.addKeyListener(player);

		Panel window = new Panel();
		window.setFocusable(true);
		window.setBackground(Color.black);
		window.setDoubleBuffered(true);

		this.add(window);
		this.setVisible(true);		
		// Solving focus bug in v0.1 
		this.requestFocusInWindow();		// Focus to capture keyboard events
	}
	
	// Thread activity
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		guiThread = Thread.currentThread();
		// Game main loop
		while(!guiThread.isInterrupted()){
			try {
				updateScreenData();
				updateGUI();
				Thread.sleep(PERIOD_IN_MS);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} 
	}

	public void start() {
		new Thread (this).start(); // Start GUI activity
	}

	/**
	 * Finish the resources associated with the game.
	 */
	private void endGame() {
		System.out.println("Terminando el juego...");
		guiThread.interrupt();
		game.endGame();
		// To be completed during the exercise
	}

	/**
	 * Set listener for exit.
	 * Called when the windows is visible
	 */
	public class CloseListener extends WindowAdapter {
		@Override
		public void windowClosing (WindowEvent e) {
			// Finish game
			endGame();
		}
	}

	/**
	 * Add listener for the GUI elements
	 * @param listener listener to handle GUI events
	 * 
	 */
	public void addListener(EventListener listener) { 
		if (listener instanceof KeyListener) {
			this.addKeyListener((KeyListener) listener);
		}    	
	}

	/**********************
	 *** Game internals ***
	 **********************/

	class Panel extends JPanel {

		/**
		 * Version 1 for this game class
		 */
		private static final long serialVersionUID = 1L;

		@Override
		/**
		 * Responsible for updating the view.
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Ensure updateGUI is already called once at least
			if (statsData != null && pacmanData != null) {
				doDrawing(g);
			}
		}
	}

	/**
	 * Draw GUI elements
	 */
	private void doDrawing(Graphics g) {				
		// Get data to draw
		int remaining_lives = statsData.remaining_lives;
		int score = statsData.score;

		// Draw
		Graphics2D g2d = (Graphics2D) g;
		view.prepareDrawing(g2d);
		view.drawMaze(g2d, mazeData);
		view.drawScore(g2d, score, remaining_lives);
		view.doAnim();
		view.drawPacman(g2d, pacmanData);
		for (int index = 0; index < ghostsData.length; index++) {
			view.drawGhost (g2d, ghostsData[index]);
		}
		if (ghostPlayerData != null) {
			view.drawGhostPlayer(g2d, ghostPlayerData);
		}
		view.finalizeDrawing(g2d);
	}

	private void updateGUI() {
		// Solving focus bug in v0.1 
		this.requestFocusInWindow();
		this.repaint();
	}

	/**
	 * Update screen-relate data for the current game
	 */
	private void updateScreenData() {
		ScreenData screenData = game.getScreenData();
		pacmanData = screenData.getPacman();
		ghostsData = screenData.getGhosts();
		mazeData = screenData.getMaze();
		statsData = screenData.getStats();
		ghostPlayerData = screenData.getGhostPlayer();
	}

}
