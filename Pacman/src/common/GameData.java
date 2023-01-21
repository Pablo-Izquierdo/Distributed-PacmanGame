package common;
/**
 * 
 * Parámetros de configuración del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class GameData {

    /**
     * Maximum number of pacman lives for the game
     */
    public static final int MAX_LIVES = 2;

    /**
     * Number of ghosts characters for the game
     */
    public static final int MAX_GHOSTS = 6;

    // Screen internals 
    public static final int BLOCK_SIZE = 24;
    public static final int MAX_BLOCKS = 15; // 15 rows x 15 columns = 225 spots
    public static final int PLAY_SCREEN_SIZE = MAX_BLOCKS * BLOCK_SIZE;

    // Pacman internals
    public static final int PACMAN_START_X = 7 * BLOCK_SIZE;	// Pacman starts at the seven block in x-axis
    public static final int PACMAN_START_Y = 11 * BLOCK_SIZE;
    public static final int PACMAN_SPEED = BLOCK_SIZE / 4;		// Speed must be multiple of BLOCK_SIZE

    // Ghosts internals
    public static final int GHOST_START_X = 4 * BLOCK_SIZE;
    public static final int GHOST_START_Y = 4 * BLOCK_SIZE;
    public static final int VALID_SPEEDS[] = {1, 2, 3, 4};
    public static final int GHOST_MAX_SPEED = BLOCK_SIZE / 6;

}
