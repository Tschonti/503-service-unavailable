package equipments;
import main.Effect;
import main.Collectable;
import main.Virologist;
import main.Inventory;
import agents.Agent;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * An abstract class, which is the parent of all the different Equipments in the game.
 */
public abstract class Equipment implements Effect, Collectable {
    /**
     * Creates a clone of itself, and puts it in the received inventory.
     * @param inv The Inventory, where the Equipment puts the clone.
     */
    public void collect(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collect",
                OutputObject.generateParamsArray(inv)
        );
        inv.addEquipment(this);
        Initializer.returnWrite(null);
    }

    /**
     * The Equipments impact in case of a Virologist throws an Agent on another Virologist.
     * @param agent The used Agent.
     * @param from The throwing Virologist.
     * @param to The Virologist, who gets thrown.
     */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                OutputObject.generateParamsArray(agent, from, to)
        );
        Initializer.returnWrite(null);
    }

    /**
     * The Equipments impact at the start of the Virologists turn.
     * @param to The Virologist on turn.
     */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    /**
     * The Equipments impact at the end of the Virologists turn.
     * @param to The Virologist on turn.
     */
    public void endTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "endTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    /**
     * Tells if with this Equipment a Virologist is allowed to be robbed.
     * Always returns false
     * @return true, if a Virologist with this Equipment can be stolen from.
     */
    public boolean allowStealing() {
        Initializer.functionWrite(
                new OutputObject(this),
                "allowStealing",
                null
        );
        Initializer.returnWrite(new OutputObject(false));
        return false;
    }

    /**
     * Decrements the Equipments time. If it expires, removes itself from the Virologist.
     * @param v The owner Virologist.
     */
    public void decrement(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "decrement",
                OutputObject.generateParamsArray(v)
        );
        Initializer.returnWrite(null);
    }

    /**
     * Creates a new standard Equipment and returns it.
     * @return The new Equipment.
     */
    public abstract Collectable cloneCollectable();
}