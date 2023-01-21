package playPartition;

import common.Cell;
import common.Position;
import common.Sprite;
import common.Direction;
import common.GameData;
import common.PlayerType;
/**
 * 
 * Implementación de la clase que representa a un caracter que puede ser controlado 
 * por un jugador humano.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class Character {

	private Sprite character;
	private final Position initialPosition;

	public Character (PlayerType type) {
		int startx = 0;
		int starty = 0;
		int speed = 1;
		switch (type) {
			case GHOST:
				startx = GameData.GHOST_START_X;;
				starty = GameData.GHOST_START_Y;
				speed = GameData.GHOST_MAX_SPEED;
				break;
			case PACMAN:
				startx = GameData.PACMAN_START_X;
				starty = GameData.PACMAN_START_Y;
				speed = GameData.PACMAN_SPEED;
				break;
		}
		character = new Sprite (startx, starty, Direction.LEFT, speed, type);
		this.initialPosition = new Position (startx, starty);
	}

	/**
	 * Moves character's current position 
	 * @param cell Cell for the current position
	 * @param nextDirection Next direction to move the character
	 * @return Character's current position + direction (sprite)
	 */
	public Sprite moveCharacter (Direction nextDirection, Cell cell) {
		int next_x = character.getX();
		int next_y = character.getY();
		Direction local_next_direction;
		// No key is pressed, keep direction
		if (nextDirection == null) {
			nextDirection = character.getDirection();
		}

		if (character.isBlockAligned()) {
			local_next_direction = nextDirection;
            // Check if we can perform the requested move
            // and update x or y axis according to the movement
            switch (local_next_direction) {
                case LEFT:
                    if (!cell.isLeftBorder()) {
                        next_x = character.getX() - character.getSpeed();
                    }
                    break;
                case RIGTH:
                    if (!cell.isRightBorder()) {
                        next_x = character.getX() + character.getSpeed();
                    }
                    break;
                case DOWN:
                    if (!cell.isBottomBorder()) {
                        next_y = character.getY() + character.getSpeed();
                    }
                    break;
                case UP:
                    if (!cell.isTopBorder()) {
                        next_y = character.getY() - character.getSpeed();
                    }
                    break;
            }
		} else {
			// Block is not reached, keep smooth movement by following the previous direction
			// Update x or y axis according to the movement
			local_next_direction = character.getDirection();
			switch (local_next_direction) {
			case LEFT:
				next_x = character.getX() - character.getSpeed();
				break;
			case RIGTH:
				next_x = character.getX() + character.getSpeed();
				break;
			case DOWN:
				next_y = character.getY() + character.getSpeed();
				break;
			case UP:
				next_y = character.getY() - character.getSpeed();
				break;
			}
		}

		// Update variables
		character.setX(next_x);
		character.setY(next_y);
		character.setDirection(local_next_direction);
		return character;
	}

	/**
	 * Reset pacman' values (such as position or direction)
	 */
	public void resetCharacter () {
		character.setX(initialPosition.getX());
		character.setY(initialPosition.getY());
		character.setDirection(Direction.LEFT);
	}
	
	/**
	 * Get the current position of the character
	 * @return the position of the character
	 */
	public Position getPosition () {
		// character data should be only modified from Character so make a copy
		Sprite clone = new Sprite (character.getX(), character.getY(), character.getDirection(), character.getSpeed(), character.getType());
		return clone;
	}
}
