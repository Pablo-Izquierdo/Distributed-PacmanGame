package playPartition;

import common.LogServiceI;
import common.PlayerType;
/**
 * 
 * Implementación de la clase principal del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class PacmanLauncher {

    public static void main(String[] args) {
        String playerName = null;
        if (args.length > 0) {
            playerName = args[0];
        }			
        
		//zeBuilderAlgorithmI builder = new MazeBuilderText();  	// maze data generator
        Maze maze = new Maze ();     						// maze data (Proxy)
		
        Character pacman = new Character (PlayerType.PACMAN);		// the player
        GhostsCPU ghosts = new GhostsCPU ();						// ghosts characters
        
        LogServiceI logger = new LogService(); 						// logger  	
        GameStats gamestats = new GameStats (playerName, logger); 	// game statistics 
        Board game = new Board(maze, ghosts, pacman);
    	GameControl board = new GameControl(game, gamestats);		// Game logic

        HumanController player = new HumanController(PlayerType.PACMAN, board);	// Player controller
        GUI gui = new GUI (board, player);										// GUI
        gui.start();															// Start gui for pacman player
        
    }
}
