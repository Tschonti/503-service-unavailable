package tiles;

import equipments.Bag;
import equipments.Equipment;
import equipments.Glove;
import equipments.ProtectiveCloak;
import java.util.Random;
import main.Collectable;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * A safehouse tile where players can pick up equipments.
 */
public class Safehouse extends Tile {

    /**
     * The equipment that can be picked up here.
     */
    private Equipment equipment;

    /**
     * Constructor
     * Creates a new equipment.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     */
    public Safehouse(int id, String name) {
        super(id, name);
        //TODO??
        Random rnd = new Random();
        switch (rnd.nextInt(3)) {
            case 0:
                equipment = new Bag();
                break;
            case 1:
                equipment = new Glove();
                break;
            case 2:
                equipment = new ProtectiveCloak();
                break;
        }
    }

    /**
     * Constructor
     * Creates a new equipment.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param eq Equipment on the tile.
     */
    public Safehouse(int id, String name, Equipment eq) {
        super(id, name);
        equipment = eq;
    }

    /**
     * Constructor for skeleton.
     * Creates new equipment.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param c Class of the equipment on the tile.
     */
    public Safehouse(int id, String name, Class c) {
        super(id, name);
        //TODO?
        try {
            equipment = (Equipment) c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
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
