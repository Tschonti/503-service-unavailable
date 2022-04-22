package equipments;

import main.Collectable;
import main.Inventory;
import main.Virologist;

/**
 * An Equipment, which the Virologist can use to Kill other Virologists.
 */
public class Axe extends Equipment implements UsableEquipment {

    /**
     * Constructor
     */
    public Axe() {
        usesLeft = 1;
    }



    /**
     * Creates a clone of itself, and puts it in the received inventory.
     * @param inv The Inventory, where the Equipment puts the clone.
     */
    @Override
    public void collect(Inventory inv) {
        inv.addUsableEquipment(this);
        super.collect(inv);
    }

    /**
     * Creates a new Axe and returns it.
     * @return The new Axe.
     */
    public Collectable cloneCollectable() {
        return new Axe();
    }

    /**
     * Hits and kills the Virologist.
     * * @param from The Virologist, who uses the Axe.
     * @param to The Virologist, who gets hit by the Axe.
     */
    public void use(Virologist from, Virologist to) {
        if (usesLeft > 0) {
            to.getActiveTile().removeVirologist(to);
            Virologist.getController().removeVirologist(to);
            this.durabilityDecreases();
            if (usesLeft == 0) {
                from.getInventory().removeUsableEquipment(this);
            }
        }
    }

    /**
     * The Equipment's toString. Used when playing in the Console.
     * @return Name of the Equipment and uses left of the Equipment.
     */
    @Override
    public String toString() {
        return "Axe: " + usesLeft + " uses left";
    }
}
