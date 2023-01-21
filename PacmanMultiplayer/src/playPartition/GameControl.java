package playPartition;

import common.GameData; 
import common.Position;
import common.ScreenData;
import common.Sprite;
import common.Stats;

import java.util.List;

import common.Cell;
import common.Direction;
import common.PlayerType;
/**
*
* Lógica que controla el juego a alto nivel.
*
* Remake of a Pacman arcade game based on versions authored by
* Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
*
* @author RCSD and TODO
* @version 2021
*/
public class GameControl extends Thread {

    private Board board;					//  Game logic for characters and maze
    private GameStats gameStats = null;		//  Game statistics

    private boolean alive = true; 			//  Check if pacman is alive
    private Sprite  pacmanData; 			//  Current position & direction for Pacman
    private Sprite  ghostPlayerData;  		//  Current position & direction for GhostPlayer
    private Position [] ghostsData = null;	//  Current position of ghosts

    private Direction pacmanNextDirection = Direction.LEFT;
    private boolean pacmanPlayerInGame = false;
    private boolean onlyPacmanPlayerInGame = true;

    private Direction ghostPlayerNextDirection = Direction.LEFT;
    private boolean ghostPlayerInGame = false;

    private Thread gameThread;
    private final int PERIOD_IN_MS = 40;	// Represents the Game update interval.
    private int currentPlayers = 0;         // Number of players registered for the game
    /**
     * Constructor
     * @param board The logic for the game
     * @param gameStats The stats of the game
     */
    public GameControl(Board board, GameStats gameStats) {
        this.board = board;
        this.gameStats = gameStats;
        resetGameVariables ();
        this.start();
    }

    // Thread activity
    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run() {
        gameThread = Thread.currentThread();
        boolean waitingForPlayers = true;
        int totalPlayers = board.getNumberOfPlayers();
        // For 1 player, pacman should be the character
        if (totalPlayers != 1) {
            onlyPacmanPlayerInGame = false;
        }
            
        // Game init loop
        while(!gameThread.isInterrupted() && waitingForPlayers){
            try {
                // Waiting for players to arrive
                if (currentPlayers == totalPlayers && pacmanPlayerInGame) {
                    waitingForPlayers = false;
                }
                Thread.sleep(PERIOD_IN_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Game main loop
        while(!gameThread.isInterrupted()){
            try {
                playGame();
                Thread.sleep(PERIOD_IN_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } 
    }

    /**
     * Main method for playing the game
     * 1. Check if Pacman is alive
     * 2. Move Pacman according the last pressed key
     * 3. Move ghosts randomly
     * 4. Check if current level has been completed
     */
    public synchronized void playGame() {
        boolean levelCompleted = false;
        if (!alive) {
            death();
        } 
        else {
            playPacman();
            playGhosts();
            alive = checkAlive();
            levelCompleted = board.checkMaze();
            if (levelCompleted) {
                initGameLevel();
                resetGameVariables();        // Reset game variables
            }
        }
    }

    /**
     * Register a player in the game
     * @param playerType Type of character controlled by the player
     */
    public synchronized void registerPlayer(PlayerType playerType) {
        if (onlyPacmanPlayerInGame && playerType != PlayerType.PACMAN) {
            return; // No more players allowed
        }
        
        switch (playerType) {
            case PACMAN:
                pacmanPlayerInGame  = true;
                break;
            case GHOST:
                ghostPlayerInGame  = true;
                break;
        }
        currentPlayers++; // TODO: Handle an erroneous number of players
    }

    /**
     * Set next direction for a character controlled by a player
     * @param playerType Character to set the new direction
     * @param nextDirection The new direction
     */
    public synchronized void setPlayerDirection(PlayerType playerType, Direction nextDirection) {
        switch (playerType) {
            case PACMAN:
                pacmanNextDirection = nextDirection;
                break;
            case GHOST:
                ghostPlayerNextDirection = nextDirection;
                break;
        }
    }


    /**
     * Obtain screen-related data for the current game
     * @return ScreenData
     */
    public synchronized ScreenData getScreenData() {
        Stats currentStats = gameStats.getStats();
        List<Cell> mazeData = board.getMazeData();
        // Pack screen data in container class
        ScreenData screenData = new ScreenData (currentStats, mazeData, ghostsData, pacmanData, ghostPlayerData); 
        return screenData;
    }
    

    public void endGame() {
        gameThread.interrupt();
        // To be completed during the exercise

    }

    /**********************
     *** Game internals ***
     **********************/

    /**
     * Pacman movements within the screen
     */
    private void playPacman() {
        Stats currentStats = null;
        currentStats = gameStats.getStats();      
        if (board.removePointFromPosition()) {// Remove point from current location if available
            currentStats.score++;
            gameStats.updateScore(currentStats.score);
        }
        pacmanData = board.movePacman(pacmanNextDirection);	
    }

    /**
     * Ghosts movements within the screen
     */
    private void playGhosts() {
        ghostsData = board.moveGhosts ();
        if (ghostPlayerInGame) {
            ghostPlayerData = board.moveGhostPlayer(ghostPlayerNextDirection);
        }
    }

    /**
     * Check if game is over
     */
    private void death() {
        Stats currentStats = null;
        boolean reset = false;
        currentStats = gameStats.getStats();
        reset = currentStats.remaining_lives <= 0;
        if (reset) { 
            gameStats.resetStats ();
        }
        board.continueGame(reset);
        resetGameVariables();  	// Reset game variables
    }


    /**
     * Init new level. Current version only reset the game to init values
     */
    private void initGameLevel() {
        board.continueGame(false);
    }

    /**
     * Reset game variables
     */
    private void resetGameVariables() {
        alive = true;
    }

    /**
     * Check if pacman is alive
     * @return true if pacman is alive, false otherwise
     */
    private boolean checkAlive () {
        boolean still_alive = true;

        // CPU Ghosts
        for (int i = 0; i < GameData.MAX_GHOSTS; i++) {
            if (is_collission (pacmanData, ghostsData[i])) {
                still_alive = false;
            }
        }
        // GhostPlayer
        if (ghostPlayerInGame) {
            if (is_collission (pacmanData, ghostPlayerData)) {
                still_alive = false;
            }
        }

        if (!still_alive) {
            gameStats.decreaseRemainingLives();
        }

        return still_alive;
    }


    /**
     * Check if there is a collision in the screen between characters
     * @param pacman Player's character
     * @param ghost Player or CPU character
     * @return true if a collision took place, false otherwise
     */
    private boolean is_collission (Position pacman, Position ghost) {
        final int RADIO = 12;
        boolean returnData = pacman.getX() > (ghost.getX() - RADIO) && pacman.getX() < (ghost.getX() + RADIO) && 
            pacman.getY() > (ghost.getY() - RADIO) && pacman.getY() < (ghost.getY() + RADIO);
            return returnData;
    }
}

