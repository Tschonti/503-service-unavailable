package tiles;

import main.Collectable;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

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
        super(id, name);
        Initializer.functionWrite(
            new OutputObject(this),
            "constructor",
            OutputObject.generateParamsArray(id, name)
        );
        Initializer.returnWrite(null);
    }

    /**
     * Clones the collectable of the field and calls its collect method,
     * that'll eventually put the new collectable into the inventory.
     * Since nothing can be picked up from here, this method does nothing.
     * @param inv The clone of the Collectable has to be stored in this inventory.
     */
    public void collectItem(Inventory inv) {
        Initializer.functionWrite(
            new OutputObject(this),
            "collectItem",
            OutputObject.generateParamsArray(inv)
        );

        Initializer.returnWrite(null);
    }

    /**
     * Returns the collectable that can be picked up from this tile.
     * @return Null, since nothing can be picked up from here.
     */
    public Collectable getCollectableItem() {
        Initializer.functionWrite(new OutputObject(this), "getCollectableItem", null);
        Initializer.returnWrite(null);

        return null;
    }
}
