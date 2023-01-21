package common;

import java.io.Serializable;

/**
 * 
 * Implementación de la clase que representa un sprite del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class Sprite extends Position implements Serializable{

	private static final long serialVersionUID = 1L; //Permite al objeto ser enviado por la red con RMI
	/**  
	 * Sprite includes character's position, direction and speed
	 *
	 */
    private Direction direction;
    private final int speed;
    private final PlayerType type;

    /**
     * Gets sprite's direction
     * @return the direction of the sprite
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set sprite's direction
     * @param direction New direction for the character
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Gets sprite's speed
     * @return the speed of the sprite
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Gets sprite's type
     * @return the type of the sprite (possible values: Pacman, Ghost)
     */
    public PlayerType getType() {
        return type;
    }

    /**
     * Constructor
     * @param x X coordinates for the character
     * @param y Y coordinates for the character
     * @param direction Direction for the character
     * @param speed Speed for the character
     * @param type Type of character
     */
    public Sprite (int x, int y, Direction direction, int speed, PlayerType type) {
        super (x,y);
        this.direction = direction;
        this.speed = speed;
        this.type = type;
    }
}
