package playPartition;

import java.util.List;  
import common.Cell;
import common.Position;
import common.Sprite;
import common.Direction;
/**
 * 
 * Implementación del tablero del juego que sirve de intermediaria
 * entre los elementos relevantes en la ejecución del juego (laberinto, pacman y ghosts).
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class Board {

	private Maze maze = null;     			// screendata
	private Character pacman = null; 		// pacman player
	private Character ghostPlayer = null; 	// ghost player
	private GhostsCPU ghosts = null; 		// all the ghosts controlled by the CPU
	private final int players;              // number of controlled characters

	/**
	 * Constructor
	 * @param maze The maze for the game
	 * @param ghosts The characters controlled by the cpu
	 * @param pacman The character controlled by the player
	 */
	public Board (Maze maze, GhostsCPU ghosts, Character pacman) {
		this.maze = maze;
		this.pacman = pacman;
		this.ghosts = ghosts;
		this.players = 1;
		this.ghosts.resetGhosts();
	}
	
	/**
	 * Constructor
	 * @param maze The maze for the game
	 * @param ghosts The characters controlled by the cpu
	 * @param pacman The character controlled by first player
	 * @param ghostPlayer The character controlled by second player
	 */
	public Board (Maze maze, GhostsCPU ghosts, Character pacman, Character ghostPlayer) {
		this.maze = maze;
		this.pacman = pacman;
		this.ghosts = ghosts;
		this.ghostPlayer = ghostPlayer;
		this.players = 2;                 // A second player controls one of the ghost
		this.ghosts.resetGhosts();
	}
	
	/**
     * Obtain the number of human players available in the board game
     * @return The number of players for the game
     */
	public int getNumberOfPlayers () {
	    return players;
	}

	/**
	 * Gets complete maze info
	 * @return A list of cells representing the current maze
	 */
	public List<Cell> getMazeData () {
		return maze.getMazeDataList();
	}

	/**
	 * Moves character's position 
	 * @param direction The new direction
	 * @return Character's sprite (i.e., current position + direction)
	 */
	public Sprite movePacman(Direction direction) {
		Sprite returnData = null;
		Cell cell = null;
		Position pacmanPosition = pacman.getPosition();

		int pos = pacmanPosition.getBlockNumber();
		cell = maze.getMazeDataList().get(pos); 
		returnData = pacman.moveCharacter(direction, cell);
		return returnData;
	}

	/**
	 * Moves ghosts' position
	 * @return Array of ghosts' position
	 */
	public Position[] moveGhosts () {
		Position [] returnData = null;
		List <Cell> mazeData = maze.getMazeDataList();
		returnData = ghosts.moveGhosts(mazeData);
		return returnData;
	}

	/**
	 * Moves ghost controlled by player
	 * @param direction The new direction
	 * @return Character's sprite (i.e., current position + direction)
	 */
	public Sprite moveGhostPlayer(Direction direction) {
		Sprite returnData = null;
		Cell cell = null;
		Position ghostPosition = ghostPlayer.getPosition();

		int pos = ghostPosition.getBlockNumber();
		cell = maze.getMazeDataList().get(pos); 
		returnData = ghostPlayer.moveCharacter(direction, cell);
		return returnData;
	}
	
	/**
	 * Reset game to default values
	 * @param reset Determine if full reset must be applied
	 */
	public void continueGame(boolean reset) {
		final int resetLevel = 0;

		ghosts.resetGhosts();
		pacman.resetCharacter();
		if (ghostPlayer != null) {
			ghostPlayer.resetCharacter();
		}
		
		if (reset) { 
			maze.initMazeData(resetLevel);
		}     
	}

	/**
	 * Check if player has finished the maze
	 * @return true if player has finished the maze, false otherwise
	 */
	public boolean checkMaze() {
		boolean returnData = false;
		returnData = maze.checkMaze();
		return returnData;
	}

	/**
	 * Check if point is available at current position
	 * @return true if player has removed the point, false otherwise
	 */
	public boolean removePointFromPosition() {
		boolean returnData = false;
		Position pacmanPosition = pacman.getPosition();
		int pos = pacmanPosition.getBlockNumber();

		if (pacmanPosition.isBlockAligned()) {  
			returnData = maze.removePoint(pos);
		}
		return returnData;
	} 
}
