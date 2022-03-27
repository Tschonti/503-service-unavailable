package tiles;

import equipments.Bag;
import equipments.Equipment;
import equipments.Glove;
import equipments.ProtectiveCloak;
import main.Collectable;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.Random;

/**
 * A safehouse tile where players can pick up equipments
 */
public class Safehouse extends Tile {
    private Equipment equipment;        // The equipment that can be picked up here

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

        Initializer.returnWrite(null);
    }

    /**
     * Constructor
     * Creates a new equipment
     * @param id Unique identifier of the tile
     * @param name Name of the tile
     * @param eq Equipment on the tile
     */
    public Safehouse(int id, String name, Equipment eq) {
        super(id, name);

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        equipment = eq;

        Initializer.returnWrite(null);
    }

    /**
     * Constructor for skeleton
     * Creates a new equipment
     * @param id Unique identifier of the tile
     * @param name Name of the tile
     * @param c Class of the equipment on the tile
     */
    public Safehouse(int id, String name, Class c) {
        super(id, name);
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        try {
            equipment = (Equipment)c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

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
