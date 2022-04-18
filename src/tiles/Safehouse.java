package tiles;

import equipments.*;
import java.util.Random;
import main.Collectable;
import main.Inventory;

/**
 * A safehouse tile where players can pick up equipments.
 */
public class Safehouse extends Tile {

    /**
     * The equipment that can be picked up here.
     */
    private final Equipment equipment;

    /**
     * Constructor
     * Sets to collectable equipment to be the one received as a paramter.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param eq Equipment on the tile.
     */
    public Safehouse(int id, String name, Equipment eq) {
        super(id, name);
        equipment = eq;
    }

    /**
     * Clones the collectable of the field and calls its collect method,
     * that'll eventually put the new collectable into the inventory.
     * In this case, it clones the equipment and calls its collect method.
     * @param inv The clone of the Collectable has to be stored in this inventory.
     */
    public void collectItem(Inventory inv) {
        equipment.cloneCollectable().collect(inv);
    }

    /**
     * Returns the collectable that can be picked up from this tile.
     * @return The equipment that can be picked up here.
     */
    public Collectable getCollectableItem() {
        return equipment;
    }
}
