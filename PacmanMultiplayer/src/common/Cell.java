package common;

import java.io.Serializable;

/**
 * 
 * Implementación de la clase que representa una celda/bloque de 
 * la pantalla del juego.
 * 
 * Remake of a Pacman arcade game based on versions authored by  
 * Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
 * 
 * @author RCSD and TODO
 * @version 2021
 */
public class Cell implements Serializable{

	private static final long serialVersionUID = 1L; //Permite al objeto ser enviado por la red con RMI
	// Constants to denote the cell's content
    // Number 1 is a left border. 
    // Numbers 2, 4 and 8 represent top, right, and bottom borders respectively. 
    // Number 16 stands for a point
    // These numbers can be added: e.g., number 19 in the upper left corner means that the square will have top and left borders and a point (16 + 2 + 1).
    private static final int POINT         = 16; // Constant for point in a cell
    private static final int EMPTY         = 0;  // Constant for empty in a cell
    private static final int LEFT_BORDER   = 1;
    private static final int TOP_BORDER    = 2;
    private static final int RIGHT_BORDER  = 4;
    private static final int BOTTOM_BORDER = 8;

    private int content = EMPTY;          // Default value for a cell

    public Cell(int data) {
        content = data;
    }

    /**
     * Retrieve cell's content
     * @return current cell's value as an int
     */
    public int getContent() {
        return content;
    }

    /**
     * Remove point from cell
     * @return true if point has been removed, false otherwise
     */
    public boolean removePoint () {
        if (!isPoint()){
            return false;
        }
        this.content = this.content - POINT;
        return true;
    }

    /**
     * Check if cell contains a point
     * @return true if cell contains a point, false otherwise
     */
    public boolean isPoint() {
        return ((content & POINT) != 0);
    }

    /**
     * Check if cell contains a left border
     * @return true if cell contains a left border, false otherwise
     */
    public boolean isLeftBorder() {
        return ((content & LEFT_BORDER) != 0);
    }

    /**
     * Check if cell contains a right border
     * @return true if cell contains a right border, false otherwise
     */
    public boolean isRightBorder() {
        return ((content & RIGHT_BORDER) != 0);
    }

    /**
     * Check if cell contains a top border
     * @return true if cell contains a top border, false otherwise
     */
    public boolean isTopBorder() {
        return ((content & TOP_BORDER) != 0);
    }

    /**
     * Check if cell contains a bottom border
     * @return true if cell contains a bottom border, false otherwise
     */
    public boolean isBottomBorder() {
        return ((content & BOTTOM_BORDER) != 0);
    }

    /**
     * Check if cell contains borders everywhere
     * @return true if cell contains all borders, false otherwise
     */
    public boolean isAllBorders() {
        final int ALL_BORDERS = BOTTOM_BORDER + TOP_BORDER + LEFT_BORDER + RIGHT_BORDER;
        return ((content & ALL_BORDERS) == ALL_BORDERS);
    }
}