package common;

/**
 * 
 * Enumerado que representa las direcciones de los personajes del juego.
 * 
 * @author RCSD
 * @version 2021
 */
public enum Direction {
    LEFT	(0), 
    RIGTH	(1), 
    UP		(2), 
    DOWN	(3);
	
    private final int id;   // id de cada dirección
    
    Direction(int id) {
        this.id = id;
    }
    
    
    /**
     * Obtiene el id de la dirección
     * @return el id de la dirección
     */
    public int obtenId () {
    	return id;
    }
}
