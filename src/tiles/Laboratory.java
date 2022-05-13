package tiles;

import java.awt.*;
import main.GeneticCode;

/**
 * A laboratory tile where virologists can learn genetic codes.
 */
public class Laboratory extends Tile {

    /**
     * Constructor
     * Sets the code to be the one received as a parameter.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param gc The GeneticCode that can be learnt here
     */
    public Laboratory(int id, String name, GeneticCode gc, Color c) {
        super(id, name, gc, c);
    }

    public Laboratory(int id, String name, GeneticCode gc) {
        super(id, name, gc, Color.ORANGE);
    }
}
