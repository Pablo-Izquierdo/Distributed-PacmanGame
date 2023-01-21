package common;

import java.io.Serializable;

/**
 * 
 * Implementación de la clase que representa una posición en el juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class Position implements Serializable{

	//version 1 de la clase utilizada por MazeRemoto
	private static final long serialVersionUID = 1L; //Permite al objeto ser enviado por la red con RMI
	private int x;
    private int y;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set character's x coordinate
     * @param x New x coordinate for the character
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set character's y coordinate
     * @param y New y coordinate for the character
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets character's x coordinate
     * @return The x coordinate of the character
     */
    public int getX() {
        return x;
    }

    /**
     * Gets character's y coordinate
     * @return The y coordinate of the character
     */
    public int getY() {
        return y;
    }
    
	
	public boolean isBlockAligned () {
		boolean result = (x % GameData.BLOCK_SIZE == 0 && y % GameData.BLOCK_SIZE == 0);
		return result;
	}

	 //  Determine where it is situated (position/square). There are 225 theoretical positions.
	public int getBlockNumber() {
		int pos = x / GameData.BLOCK_SIZE + GameData.MAX_BLOCKS * (int) (y / GameData.BLOCK_SIZE);
		return pos;
	}
    
    
    
}
