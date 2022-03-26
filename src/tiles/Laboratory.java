package tiles;

import main.Collectable;
import main.GeneticCode;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * A laboratory tile where virologists can learn genetic codes
 */
public class Laboratory extends Tile {
    private final GeneticCode code;

    /**
     * Constructor
     * Creates a new genetic code
     * @param id Unique identifier of the tile
     * @param name Name of the tile
     */
    public Laboratory(int id, String name) {
        super(id, name);

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                OutputObject.generateParamsArray(id, name)
        );
        code = new GeneticCode();
        Initializer.returnWrite(null);
    }

    /**
     * Clones th collectable of the field and calls its collect method,
     * that'll eventually put the new collectable into the inventory.
     * In this case, it clones the code and calls its collect method.
     * @param inv The clone of the Collectable has to be stored in this inventory.
     */
    public void collectItem(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collectItem",
                OutputObject.generateParamsArray(inv)
        );
        code.cloneCollectable().collect(inv);
        Initializer.returnWrite(null);
    }

    /**
     * Returns the collectable that can be picked up from this tile.
     * @return The genetic code that can be learnt here
     */
    public Collectable getCollectableItem() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCollectableItem",
                null
        );
        Initializer.returnWrite(new OutputObject(code));
        return code;
    }
}
