package common;
/**
 * 
 * Enumerado que representa el tipo de personaje controlado por el jugador.
 * 
 * @author RCSD
 * @version 2021
 */
public enum PlayerType {
    PACMAN	(0), 
    GHOST	(1);
	
    private final int id;   // id del tipo de personaje
    
    PlayerType(int id) {
        this.id = id;
    }
    
    
    /**
     * Obtiene el id del tipo de personaje
     * @return el id del tipo de personaje
     */
    public int obtenId () {
    	return id;
    }
}
