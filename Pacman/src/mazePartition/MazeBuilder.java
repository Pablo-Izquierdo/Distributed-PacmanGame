package mazePartition;

import java.util.ArrayList;
import java.util.List;

import common.Cell;
import common.MazeBuilderAlgorithmI;
/**
 * 
 * Implementación de la clase para construir laberintos del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class MazeBuilder {

    MazeBuilderAlgorithmI algorithm; // Algorithm used to build the maze

    /**
     * Constructor
     * @param algorithm Algorithm used to build the maze
     */
    public MazeBuilder(MazeBuilderAlgorithmI algorithm) {
        this.algorithm = algorithm;
    }  

    /**
     * Create a new maze
     * @param level Level used to create the new maze
     * @return A new maze as a list of cells
     */
    public List<Cell> createMazeData (int level) {
        List<Cell> returnData;

        byte[] allBytes = algorithm.getNewMazeData (level);

        // Create list of cells
        returnData = toCellList(allBytes);   	
        return returnData; 	
    }

    /**
     * Gets the last level available
     * @return the number of the last level available
     */
    public int lastMazeLevel () {
    	// To be completed during the exercise
        return Integer.MAX_VALUE;
    }

    /********************* 
     *** Local methods ***
     *********************/

    private List<Cell> toCellList (byte[] data) {
        ArrayList<Cell> returnData = new ArrayList<Cell> (data.length);
        for (byte nextData: data) {
            returnData.add (new Cell ((int) nextData));
        }
        return returnData;
    }

}
