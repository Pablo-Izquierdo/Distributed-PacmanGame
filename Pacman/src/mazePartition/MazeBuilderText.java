package mazePartition;

import common.MazeBuilderAlgorithmI;
/**
 * 
 * Implementación de la clase para construir laberintos del juego a 
 * través de arrays.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class MazeBuilderText implements MazeBuilderAlgorithmI {

    private int levelNumber = -1;	     		// Current maze number
    private static final int NUMBERLEVELS = 3;  // Number of available levels

    /**
     * Get data for a new maze with the corresponding level
     * @param level The level used to generate the new maze
     * @return An array of bytes with data from the new maze
     */
    @Override
    public byte[] getNewMazeData (int level) {		
        byte[] returnData = null;

        levelNumber = level % NUMBERLEVELS; 				// Maze number 
        returnData = leveldata[levelNumber].clone();

        return returnData;
    }

    /**
     * Get the last maze level available
     * @return the last level available
     */
    @Override
    public int getLastMazeLevel () {
        return this.leveldata.length; 
    }

    /********************* 
     *** Local methods ***
     *********************/

    /** 
     * Maze level data - Do not modify
     */
    private final byte[] leveldata1 = { 
        19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
        21, 0,  0,  0,  17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        21, 0,  0,  0,  17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 
        21, 0,  0,  0,  17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20, 
        17, 18, 18, 18, 16, 16, 20, 0,  17, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 20, 0,  17, 16, 16, 16, 16, 24, 20, 
        25, 16, 16, 16, 24, 24, 28, 0,  25, 24, 24, 16, 20, 0,  21, 
        1,  17, 16, 20, 0,  0,  0,  0,  0,  0,  0,  17, 20, 0,  21,
        1,  17, 16, 16, 18, 18, 22, 0,  19, 18, 18, 16, 20, 0,  21,
        1,  17, 16, 16, 16, 16, 20, 0,  17, 16, 16, 16, 20, 0,  21, 
        1,  17, 16, 16, 16, 16, 20, 0,  17, 16, 16, 16, 20, 0,  21,
        1,  17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0,  21,
        1,  17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  21,
        1,  25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
        9,  8,  8,  8,  8,  8,  8,  8,  8,  8,  25, 24, 24, 24, 28 
    };

    private final byte[] leveldata2 = { 
        0,  0, 19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,  0,  0,
        0, 19, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 22,  0,
        19, 16, 16, 16, 16, 16, 24, 24, 24, 16, 16, 16, 16, 16, 22,
        17, 16, 16, 16, 16, 20,  0,  0,  0, 17, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 20,  0,  0,  0, 17, 16, 16, 16, 16, 20,
        17, 16, 16, 24, 24, 28,  0,  0,  0, 25, 24, 24, 16, 16, 20,
        17, 16, 20,  0,  0,  0,  0,  0,  0,  0,  0,  0, 17, 16, 20,
        17, 16, 20,  0,  0,  0,  0,  0,  0,  0,  0,  0, 17, 16, 20,
        17, 16, 20,  0,  0,  0,  0,  0,  0,  0,  0,  0, 17, 16, 20,
        17, 16, 16, 18, 18, 22,  0,  0,  0, 19, 18, 18, 16, 16, 20,
        17, 16, 16, 16, 16, 20,  0, 23,  0, 17, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 20,  0, 21,  0, 17, 16, 16, 16, 16, 20,
        25, 16, 16, 16, 16, 16, 18, 16, 18, 16, 16, 16, 16, 16, 28,
        0, 25, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 28,  0,
        0,  0, 25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28,  0,  0 
    };


    private final byte[] leveldata3 = { 
        19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
        17, 16, 24, 24, 16, 16, 16, 16, 16, 24, 24, 24, 24, 16, 20,
        17, 20,  0,  0, 17, 16, 16, 16, 20,  0,  0,  0,  0, 17, 20,
        17, 20,  0,  0, 17, 16, 16, 16, 20,  0,  0,  0,  0, 17, 20,
        17, 16, 18, 18, 16, 16, 16, 16, 16, 18, 22,  0,  0, 17, 20,
        17, 16, 16, 16, 16, 16, 24, 24, 24, 16, 20,  0,  0, 17, 20,
        17, 16, 16, 16, 16, 20,  0,  0,  0, 17, 16, 18, 18, 16, 20,
        17, 16, 16, 16, 16, 20,  0,  0,  0, 17, 16, 16, 16, 16, 20,
        17, 16, 24, 24, 16, 20,  0,  0,  0, 17, 16, 16, 16, 16, 20,
        17, 20,  0,  0, 17, 16, 18, 18, 18, 16, 16, 16, 16, 16, 20,
        17, 20,  0,  0, 25, 24, 16, 16, 16, 16, 16, 24, 24, 16, 20,
        17, 20,  0,  0,  0,  0, 17,  0, 16, 16, 20,  0,  0, 17, 20,
        17, 20,  0,  0,  0,  0, 17, 16, 16, 16, 20,  0,  0, 17, 20,
        17, 16, 18, 18, 18, 18, 16, 16, 16, 16, 16, 18, 18, 16, 20,
        25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28 
    };


    // Bidimensional array for simple access
    private final byte[][]leveldata= {leveldata1, leveldata2, leveldata3}; 
}