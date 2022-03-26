package equipments;
import main.Collectable;
import main.Inventory;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * An Equipment, witch doubles a Virologists maximum collectable amount of resources.
 */
public class Bag extends Equipment {
    /**
     * Constructor
     */
    public Bag() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(new OutputObject(this));
    }

    /**
     * The Bags impact at the start of the Virologists turn.
     * Doubles the Virologists maximum collectable amount of resources.
     * @param to The Virologist on turn.
     */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Inventory i=to.getInventory();
        int amount=i.getMaxResourceAmount();
        i.setMaxResourceAmount(2*amount);
        Initializer.returnWrite(null);
    }

    /**
     * The Bags impact at the end of the Virologists turn.
     * Cuts the Virologists maximum collectable amount of resources in half.
     * @param to The Virologist on turn.
     */
    public void endTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "endTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Inventory i=to.getInventory();
        int amount=i.getMaxResourceAmount();
        i.setMaxResourceAmount(amount/2);
        Initializer.returnWrite(null);
    }

    /**
     * Creates a new Bag and returns it.
     * @return The new Bag.
     */
    public Collectable clone() {
        Initializer.functionWrite(
                new OutputObject(this),
                "clone",
                null
        );
        Initializer.returnWrite(new OutputObject(new Bag()));
        return new Bag();
    }
}