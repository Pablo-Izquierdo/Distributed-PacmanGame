package concurrente;

import common.Cell;
import common.Direction;
/**
 * 
 * Implementación de la clase GhostAI que representa
 * el manejo de un ghost por el ordenador.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class GhostAI {

    /**
     * The ghost randomly decides if direction should be changed
     * @param cellContent The content for the current cell
     * @param ghostx X coordinate for the ghost
     * @param ghosty Y coordinate for the ghost
     * @param currentDirection Direction for the ghost   
     * @return Ghost's next direction 
     */ 
    public Direction nextDirection (int cellContent, int ghostx, int ghosty, Direction currentDirection) {
        Direction[] allowedDirections = new Direction[4]; // Max 4 allowed directions (LEFT, RIGTH, UP, DOWN)
        Direction nextDirection = currentDirection;
        Cell currentCell = new Cell (cellContent);
        int count = 0;
        
        // Check which movements are allowed
        // If there is no obstacle on the left and the ghost is not already moving to the right, 
        // the ghost could move to the left. 
        if (!currentCell.isLeftBorder() && currentDirection != Direction.RIGTH) {
            allowedDirections[count] =  Direction.LEFT;
            count++;
        }

        if (!currentCell.isRightBorder() && currentDirection != Direction.LEFT) {
            allowedDirections[count] =  Direction.RIGTH;
            count++;
        }

        if (!currentCell.isTopBorder() && currentDirection != Direction.DOWN) {
            allowedDirections[count] =  Direction.UP;
            count++;
        }

        if (!currentCell.isBottomBorder() && currentDirection != Direction.UP) {
            allowedDirections[count] =  Direction.DOWN;
            count++;
        }

        // Determine next direction
        if (count > 0) {
            //  Random decision 
            count = (int) (Math.random() * count);

            if (count > 3) {
                count = 3;
            }		
            nextDirection = allowedDirections[count];

        } else {
            // No move ahead, return by the arrival path
            switch (currentDirection) {
                case UP:
                    nextDirection = Direction.DOWN;
                    break;
                case DOWN:
                    nextDirection = Direction.UP;
                    break;
                case LEFT:
                    nextDirection = Direction.RIGTH;
                    break;
                case RIGTH:
                    nextDirection = Direction.LEFT;
                    break;
            }
        }
        return nextDirection;
    }	
}
