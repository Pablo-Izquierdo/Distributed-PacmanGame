package concurrente;

import java.util.ArrayList; 
import java.util.List;

import common.Cell;
import common.MazeBuilderAlgorithmI;
/**
 * 
 * Implementación de la clase que representa el laberinto del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class Maze {
    
    private List<Cell> mazeData;	// Maze data
    private MazeBuilder builder;    // Builder responsible for creating mazes
    private int currentMazeLevel;   // Maze level from current game 

    public Maze (MazeBuilderAlgorithmI algorithm) {
        this.builder = new MazeBuilder(algorithm);
        currentMazeLevel = 0;
        initMazeData (currentMazeLevel);
    }


    /**
     * Remove data point for a particular position of the maze
     * @param pos the position in the maze
     * @return true if maze has been modified, false otherwise
     */
    public boolean removePoint (int pos) {
        return mazeData.get(pos).removePoint();
    }

    /**
     * Gets the current maze's data
     * @return The current maze as a list of cells
     */
    public List<Cell> getMazeDataList () {
        // Maze data should be only modified from maze so make a copy
        List<Cell> clone = new ArrayList<Cell> (mazeData);
        return clone;
    }

    /**
     * Check if player has finished the maze
     * @return true if player has finished the maze, false otherwise
     */
    public boolean checkMaze() {
        boolean finished = true;

        for (Cell currentCell : mazeData) {
            if (currentCell.isPoint()) { // Any remaining point?
                finished = false;
                break;
            }
        }

        if (finished) {
            currentMazeLevel++;
            int maxLevel = builder.lastMazeLevel();
            if (currentMazeLevel > maxLevel) {
                currentMazeLevel = currentMazeLevel % maxLevel;
            }
            initMazeData(currentMazeLevel);
        }
        return finished;
    }

    /**
     * Initialize level with maze data
     * @param mazeLevel Current level for the game
     */
    public void initMazeData(int mazeLevel) {       
        mazeData = builder.createMazeData(mazeLevel);
    }

}
