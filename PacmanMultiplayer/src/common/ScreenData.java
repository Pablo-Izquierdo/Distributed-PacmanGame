package common;

import java.util.List;
/**
 * 
 * Implementación de la clase que almacena información sobre los datos en pantalla asociado al juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class ScreenData {
	private final Sprite pacman;
	private Sprite ghostPlayer;
	private final Position[] ghosts;
	private final List<Cell> maze;
	private final Stats stats;


    public ScreenData (Stats stats, List<Cell> maze, Position[] ghosts, Sprite pacman) {
        this.pacman = pacman;
        this.stats = stats;
        this.maze = maze;
        this.ghosts = ghosts;
    }
    
    public ScreenData (Stats stats, List<Cell> maze, Position[] ghosts, Sprite pacman, Sprite ghostPlayer) {
        this (stats, maze, ghosts, pacman);
        this.ghostPlayer = ghostPlayer;
    }

    /**
     * Get Pacman screen information
     * @return pacman screen data 
     */
	public Sprite getPacman() {
		return pacman;
	}

    /**
     * Get Ghosts screen information
     * @return Ghosts screen data 
     */
	public Position[] getGhosts() {
		return ghosts;
	}


    /**
     * Get Maze screen information
     * @return maze screen data 
     */
	public List<Cell> getMaze() {
		return maze;
	}

    /**
     * Get Statistics screen information
     * @return statistics screen data 
     */
	public Stats getStats() {
		return stats;
	}

    /**
     * Get Ghost player screen information
     * @return ghost player screen data 
     */
	public Sprite getGhostPlayer() {
		return ghostPlayer;
	} 
}
