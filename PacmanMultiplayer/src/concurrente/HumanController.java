package concurrente;

import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import common.Direction;
import common.PlayerType;
/**
 * 
 * Clase que representa un caracter controlado por un jugador humano.
 * 
 * Remake of a Connect-4 strategy game based on version authored by Andrew Zhao
 * 
 * @author RCSD 
 * @version 2021
 */
public class HumanController extends KeyAdapter implements KeyListener {

	private GameControl board;          // Controller for the game
	private PlayerType playerType;		// Type of character controlled by the player

	public HumanController(PlayerType playerType, GameControl board) {
		this.board = board;
		this.playerType = playerType;

		this.board.registerPlayer(playerType);
	}
	/*
	 * Detects when a key is pressed.<p>
	 * In-game: Changes Character's direction of movement with the corresponding keys <p>
	 * Two player game is coded for different keyboards
	 * @param e a KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {	// TODO: Code with KeyBindings if a single keyboard is shared in 2-Player game
		Direction nextDirection = null;
		int key = e.getKeyCode();
		switch (playerType) {
			case PACMAN:
				if (key == KeyEvent.VK_LEFT) {
					nextDirection = Direction.LEFT;
				} else if (key == KeyEvent.VK_RIGHT) {
					nextDirection = Direction.RIGTH;
				} else if (key == KeyEvent.VK_UP) {
					nextDirection = Direction.UP;
				} else if (key == KeyEvent.VK_DOWN) {
					nextDirection = Direction.DOWN;
				}
				break;
			case GHOST:
				if (key == KeyEvent.VK_A) {
					nextDirection = Direction.LEFT;
				} else if (key == KeyEvent.VK_D) {
					nextDirection = Direction.RIGTH;
				} else if (key == KeyEvent.VK_W) {
					nextDirection = Direction.UP;
				} else if (key == KeyEvent.VK_S) {
					nextDirection = Direction.DOWN;
				}
				break;
		}
		// Update game with new direction
		if (nextDirection != null) {
			board.setPlayerDirection (playerType, nextDirection);
		}
	}
}
