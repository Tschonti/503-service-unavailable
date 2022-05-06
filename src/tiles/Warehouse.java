package tiles;

import java.awt.*;
import main.Resource;

/**
 * Warehouse field where virologists can pick up resources.
 */
public class Warehouse extends Tile {

    /**
     * Constructor that takes resource from parameter.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param collectable The resource that can be picked up from this tile
     */
    public Warehouse(int id, String name, Resource collectable) {
        super(id, name, collectable, Color.CYAN);
    }

    /**
     * A method to destroy the collectable of the tile.
     * Sets the collectable of the tile to null,
     * preventing virologists from picking up
     * resources from here in the future.
     */
    @Override
    public void destroyCollectable() {
        collectable = null;
    }
}
