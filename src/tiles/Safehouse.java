package tiles;

import equipments.Equipment;
import equipments.Glove;
import main.Collectable;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * A safehouse tile where players can pick up equipments
 */
public class Safehouse extends Tile {
    private final Equipment equipment;

    /**
     * Constructor
     * Creates a new equipment
     * @param id Unique identifier of the tile
     * @param name Name of the tile
     */
    public Safehouse(int id, String name) {
        super(id, name);

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        equipment = new Glove();
        Initializer.returnWrite(null);
    }

    /**
     * Clones the collectable of the field and calls its collect method,
     * that'll eventually put the new collectable into the inventory.
     * In this case, it clones the equipment and calls its collect method.
     * @param inv The clone of the Collectable has to be stored in this inventory.
     */
    public void collectItem(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collectItem",
                OutputObject.generateParamsArray(inv)
        );
        equipment.cloneCollectable().collect(inv);
        Initializer.returnWrite(null);
    }

    /**
     * Returns the collectable that can be picked up from this tile.
     * @return The equipment that can be picked up here
     */
    public Collectable getCollectableItem() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCollectableItem",
                null
        );
        Initializer.returnWrite(new OutputObject(equipment));

        return equipment;
    }
}
