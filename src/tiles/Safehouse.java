package tiles;

import equipments.Equipment;
import java.awt.*;

/**
 * A safehouse tile where players can pick up equipments.
 */
public class Safehouse extends Tile {

    /**
     * Constructor
     * Sets to collectable equipment to be the one received as a parameter.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param eq Equipment on the tile.
     */
    public Safehouse(int id, String name, Equipment eq) {
        super(id, name, eq, Color.GREEN);
    }
}
