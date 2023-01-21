package common;

/**
 * 
 * Interfaz de los servicios de log del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public interface LogServiceI {

    /**
     * Log game data
     * @param score Player's score in the game
     * @return true if data has been successfully logged, false otherwise 
     */
    public boolean logData (int score);

    /**
     * Sets the player's name
     * @param name Name for current player
     */
    public void setPlayerName (String name);

}
