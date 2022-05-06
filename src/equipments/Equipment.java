package equipments;

import agents.Agent;
import main.Collectable;
import main.Effect;
import main.Inventory;
import main.Virologist;
import observables.ObservableEquipment;

/**
 * An abstract class, which is the parent of all the different Equipments in the game.
 */
public abstract class Equipment implements Effect, Collectable {

    /**
     * How many times the Equipment can be used without breaking down.
     */
    protected int usesLeft;

    /**
     * Observable for the equipment.
     */
    private final ObservableEquipment view;

    /**
     * Constructor
     */
    public Equipment() {
        view = new ObservableEquipment(this);
    }

    /**
     * Getter for the view of the equipment
     * @return The view observing this equipment
     */
    public ObservableEquipment getView() {
        return view;
    }

    /**
     * Creates a clone of itself, and puts it in the received inventory.
     * @param inv The Inventory, where the Equipment puts the clone.
     */
    public void collect(Inventory inv) {
        inv.addEquipment(this);
    }

    /**
     * The Equipments impact in case of a Virologist throws an Agent on another Virologist.
     * @param agent The used Agent.
     * @param from The throwing Virologist.
     * @param to The Virologist, who gets thrown.
     */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {}

    /**
     * The Equipments impact at the start of the Virologists turn.
     * @param to The Virologist on turn.
     */
    public void onTurnImpact(Virologist to) {}

    /**
     * The Equipments impact at the end of the Virologists turn.
     * @param to The Virologist on turn.
     */
    public void endTurnImpact(Virologist to) {}

    /**
     * Tells if with this Equipment a Virologist is allowed to be robbed.
     * Always returns false.
     * @return true, if a Virologist with this Equipment can be stolen from.
     */
    public boolean allowStealing() {
        return false;
    }

    /**
     * Decrements the Equipments time. Returns true if it reaches 0.
     * @param v The owner Virologist.
     * @return True, if the equipment is expired
     */
    public boolean decrement(Virologist v) {
        return false;
    }

    /**
     * Decreases the value of the usesLeft variable.
     */
    public void durabilityDecreases() {
        usesLeft--;
    }

    /**
     * Creates a new standard Equipment and returns it.
     * @return The new Equipment.
     */
    public abstract Collectable cloneCollectable();

    /**
     * Infects all the virologists that are on the same tile as v with the effect.
     * Since this feature doesn't exist on any of the equipments, it does nothing.
     * @param v The virologist whose neighbours will be infected
     */
    public void infect(Virologist v) {}
}
