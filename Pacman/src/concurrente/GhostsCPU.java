package concurrente;

import java.util.List; 

import common.Cell;
import common.Direction;
import common.GameData;
import common.PlayerType;
import common.Position;
import common.Sprite;
/**
 * 
 * Implementación de la clase que representa los caracteres ghosts del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class GhostsCPU {
	private Sprite[] theGhosts;		// Array of sprites representing the ghosts controlled by the CPU
	private GhostAI ai;				// Algorithm to move the ghosts

	/** 
	 * Constructor 
	 */	
	public GhostsCPU () {
		theGhosts = new Sprite[GameData.MAX_GHOSTS];
		ai = new GhostAI(); 		// Computer-controlled ghosts
	}

	/**
	 * Moves ghosts' current position through the maze
	 * @param mazeData The maze as a list of cells
	 * @return Array of ghosts' current position
	 */
	public Position[] moveGhosts(List<Cell> mazeData) {
		Position [] returnData = new Position [GameData.MAX_GHOSTS];

		for (int ghost_index = 0; ghost_index < GameData.MAX_GHOSTS; ghost_index++) {		
			returnData[ghost_index] = moveSingleGhost (mazeData, theGhosts[ghost_index]);
		}
		return returnData;
	}

	/**
	 * Reset ghosts' values (such as position or speed)
	 */
	public void resetGhosts () {
		Direction init_direction;
		int random;
		int init_y = GameData.GHOST_START_Y;
		int init_x = GameData.GHOST_START_X;
		for (int i = 0; i < GameData.MAX_GHOSTS; i++) {		
			init_direction = Direction.values()[i % Direction.values().length];
			random = (int) (Math.random() * (GameData.VALID_SPEEDS.length));

			if (random > GameData.GHOST_MAX_SPEED) {
				random = GameData.GHOST_MAX_SPEED;
			}			
			theGhosts[i] = new Sprite (init_x, init_y, init_direction, GameData.VALID_SPEEDS[random], PlayerType.GHOST);
		}
	}

	/********************* 
	 *** Local methods ***
	 *********************/

	/**
	 * Get next position for the ghost
	 * @param mazeData Screen data
	 * @param ghost Character data
	 * @return Ghost's next position 
	 */
	private Position moveSingleGhost (List<Cell> mazeData, Sprite ghost) {
		int pos = 0;
		int next_x, next_y;
		Position returnData = null;
		Direction next_direction = ghost.getDirection(); // Keep direction by default

		//  Determine where the ghost is situated (position/square). There are 225 theoretical positions.
		pos = ghost.getBlockNumber();
		Cell currentCell = mazeData.get(pos);

		// Move only if there is some movement available
		if (currentCell.isAllBorders()) {
			// character is stalled, don't move
			returnData = new Position (ghost.getX(), ghost.getY());
			return returnData;
		}

		// Compute new direction only when a new block is reached
		if (ghost.isBlockAligned()) {
			next_direction = ai.nextDirection (currentCell.getContent(), ghost.getX(), ghost.getY(), ghost.getDirection());
		} 	
		// Update x or y axis according to the movement
		// If a new block is not reached, keep smooth movement by following the previous direction
		switch (next_direction) {
			case LEFT:
				next_x = ghost.getX() - ghost.getSpeed();
				ghost.setX(next_x);
				break;
			case RIGTH:
				next_x = ghost.getX() + ghost.getSpeed();
				ghost.setX(next_x);
				break;
			case DOWN:
				next_y = ghost.getY() + ghost.getSpeed();
				ghost.setY(next_y);
				break;
			case UP:
				next_y = ghost.getY() - ghost.getSpeed();
				ghost.setY(next_y);
				break;
		}

		// Update direction for this ghost
		ghost.setDirection(next_direction);
		// Set return data
		returnData = new Position (ghost.getX(), ghost.getY());
		return returnData;
	}
}
