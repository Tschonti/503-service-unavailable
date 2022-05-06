package tiles;

import java.awt.*;

/**
 * An empty tile of the game where no collectable can be picked up.
 */
public class EmptyTile extends Tile {

    /**
     * Constructor
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     */
    public EmptyTile(int id, String name) {
        super(id, name, Color.DARK_GRAY);
    }
}
