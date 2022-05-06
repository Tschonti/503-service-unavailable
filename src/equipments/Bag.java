package equipments;

import main.Collectable;
import main.Inventory;
import main.Virologist;

/**
 * An Equipment, witch doubles a Virologists maximum collectable amount of resources.
 */
public class Bag extends Equipment {

    /**
     * Constructor
     */
    public Bag() {
        super();
    }

    /**
     * The Bags impact at the start of the Virologists turn.
     * Doubles the Virologists maximum collectable amount of resources.
     * @param to The Virologist on turn.
     */
    public void onTurnImpact(Virologist to) {
        Inventory i = to.getInventory();
        int maxAmount = i.getMaxResourceAmount();
        i.setMaxResourceAmount(2 * maxAmount);
    }

    /**
     * The Bags impact at the end of the Virologists turn.
     * Cuts the Virologists maximum collectable amount of resources in half.
     * @param to The Virologist on turn.
     */
    public void endTurnImpact(Virologist to) {
        Inventory i = to.getInventory();
        int maxAmount = i.getMaxResourceAmount();
        i.setMaxResourceAmount(maxAmount / 2);
    }

    /**
     * Creates a new Bag and returns it.
     * @return The new Bag.
     */
    public Collectable cloneCollectable() {
        return new Bag();
    }

    /**
     * The Equipment's toString. Used when playing in the Console.
     * @return Name of the Equipment.
     */
    @Override
    public String toString() {
        return "Bag";
    }
}
