package tiles;

import main.Collectable;
import main.GeneticCode;
import main.Inventory;

/**
 * A laboratory tile where virologists can learn genetic codes.
 */
public class Laboratory extends Tile {

    /**
     * The code that can be learnt here.
     */
    protected final GeneticCode code;

    /**
     * Constructor
     * Sets the code to be the one received as a parameter.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param gc The GeneticCode that can be learnt here
     */
    public Laboratory(int id, String name, GeneticCode gc) {
        super(id, name);
        code = gc;
    }

    /**
     * Clones th collectable of the field and calls its collect method,
     * that'll eventually put the new collectable into the inventory.
     * In this case, it clones the code and calls its collect method.
     * @param inv The clone of the Collectable has to be stored in this inventory.
     */
    public void collectItem(Inventory inv) {
        code.cloneCollectable().collect(inv);
    }

    /**
     * Returns the collectable that can be picked up from this tile.
     * @return The genetic code that can be learnt here.
     */
    public Collectable getCollectableItem() {
        return code;
    }
}
