package playPartition;
import common.LogServiceI;
import common.Stats;
/**
 * 
 * Implementación de la clase para gestionar las estadísticas del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class GameStats {

    private Stats gameStats;			// Game statistics
    private LogServiceI logger;  		// Logger service

    public GameStats (String playerName, LogServiceI logger) {
        this.logger = logger;
        initVariables();
        // To be completed during the exercise
    }

    /**
     * Resets all the statistics
     */
    public void resetStats() {
        initVariables();
    }

    /**
     * Gets the statistics associated with the current game
     * @return Pacman's statistics
     */
    public Stats getStats() {
        return gameStats;
    }

    /**
     * Updates the current score for pacman
     * @param score the player's score for this game
     */
    public void updateScore (int score) {
        gameStats.score = score;		
    }

    /**
     * Decreases pacman's lives by one
     */
    public void decreaseRemainingLives() {
        gameStats.remaining_lives--;
        if (gameStats.remaining_lives == 0) {
            //  return data is not used in this version
            logger.logData(gameStats.score);
        }
    }

    /********************* 
     *** Local methods ***
     *********************/

    private void initVariables() {		
        gameStats = new Stats ();
    }

}
