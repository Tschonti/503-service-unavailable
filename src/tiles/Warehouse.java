package tiles;

import main.Collectable;
import main.Inventory;
import main.Resource;
import main.ResourceType;

import java.util.ArrayList;

/**
 * Warehouse field where virologists can pick up resources.
 */
public class Warehouse extends Tile {

    /**
     * The resource that can be picked up here.
     */
    private Resource collectable;

    /**
     * Constructor that takes resource from parameter.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param collectable The resource that can be picked up from this tile
     */
    public Warehouse(int id, String name, Resource collectable) {
        super(id, name);
        this.collectable = collectable;
    }

    /**
     * Clones the collectable of the field and calls its collect method,
     * that'll eventually put the new collectable into the inventory.
     * In this case, it clones the resource and calls its collect method.
     * @param inv The clone of the Collectable has to be stored in this inventory.
     */
    public void collectItem(Inventory inv) {
        if (collectable != null) {
            collectable.cloneCollectable().collect(inv);
        }
    }

    /**
     * Returns the collectable that can be picked up from this tile.
     * @return The resource that can be picked up here.
     */
    public Collectable getCollectableItem() {
        return collectable;
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
