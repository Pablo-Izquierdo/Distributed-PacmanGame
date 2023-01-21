package common;

/**
*
* Implementación de la clase que representa las estadísticas gestionadas
* en el juego.
*
* Remake of a Pacman arcade game based on versions authored by
* Brian Postma, Jan Bodnar, Dario Castellanos, Brandon Newman and Daniel Ly
*
* @author RCSD and TODO
* @version 2021
*/
public class Stats {

    public int score;
    public int remaining_lives;

    /**
     * Simple constructor with default values
     */
    public Stats () {
        this.score = 0;
        this.remaining_lives = GameData.MAX_LIVES;
    }

    /**
     * Constructor for specific values
     * @param score The score of the player
     * @param remaining_lives The number of remaining lives for the player
     */
    public Stats (int score, int remaining_lives) {
        this.score = score;
        this.remaining_lives = remaining_lives;
    }
}
