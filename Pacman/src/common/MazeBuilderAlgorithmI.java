package common;
/**
 * 
 * Interfaz de los algoritmos de construcción de laberintos.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public interface MazeBuilderAlgorithmI {

    /**
     * Get data for a new maze with the corresponding level
     * @param level The level used to generate the new maze
     * @return An array of bytes with data from the new maze
     */
    public byte[] getNewMazeData (int level);

    /**
     * Get the last maze level available
     * @return the last level available
     */
    public int getLastMazeLevel ();

}
