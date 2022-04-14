package tiles;

import agents.Agent;
import agents.Vaccine;
import main.Collectable;
import main.GeneticCode;
import main.Inventory;

import java.util.ArrayList;

/**
 * A laboratory tile where virologists can learn genetic codes.
 */
public class Laboratory extends Tile {

    /**
     * The code that can be learnt here.
     */
    private final GeneticCode code;

    /**
     * Constructor
     * Creates a new genetic code.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     */
    public Laboratory(int id, String name) {
        super(id, name);
        code = new GeneticCode(new Vaccine(), new ArrayList<>());//TODO
    }

    /**
     * Constructor
     * Creates a new genetic code with the given agent.
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param a The agent that will be created by crafting from this code.
     */
    public Laboratory(int id, String name, Agent a) {
        super(id, name);
        code = new GeneticCode(a, new ArrayList<>());//TODO
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
