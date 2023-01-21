package playPartition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import common.LogServiceI;
/**
 * 
 * Implementación del servicio de log que utiliza ficheros de texto.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class LogService implements LogServiceI {

    private String playerName = "Default Player";        // Name of the player
    private static final String fileName = "pacman.log"; // Name of the log file
    private File file   = null;							 // Records file

    /**
     * Log game data in a file
     * @param score: Player's score in the game
     * @return true if data has been successfully logged, false otherwise 
     */
    public boolean logData (int score) {
        boolean returnData = true;
        try {
            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile(); // create file if it does not exist
            }
            FileWriter writer = new FileWriter(file, true); // Append data
            writer.write(playerName+": "+score+System.getProperty( "line.separator" ));
            writer.close();
        } catch (IOException e) {
            // For simplicity, remain silent about it
            returnData = false; // Data is not logged
        }
        return returnData;
    }

    /**
     * Sets the player's name
     * @param name Name for current player
     */
    public void setPlayerName (String name) {
        playerName = name;
    }
}
