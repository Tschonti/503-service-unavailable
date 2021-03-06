package equipments;

import agents.Agent;
import main.Collectable;
import main.Virologist;

/**
 * An Equipment, which throws back a thrown Agent to the thrower Virologist.
 */
public class Glove extends Equipment {

    /**
     * Constructor
     */
    public Glove() {
        super();
        usesLeft = 3;
    }

    /**
     * The Gloves impact in case of a Virologist throws an Agent on another Virologist.
     * Removes the used Agent from the thrown Virologists effects, and adds it to the thrower Virologists effects.
     * @param agent The used Agent.
     * @param from The throwing Virologist.
     * @param to The Virologist, who gets thrown.
     */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        to.removeEffect(agent);
        if (from != null) from.addEffect(agent);
        durabilityDecreases();
        if (usesLeft == 0) {
            to.getInventory().removeEquipment(this);
        }
    }

    /**
     * Creates a new Glove and returns it.
     * @return The new Glove.
     */
    public Collectable cloneCollectable() {
        return new Glove();
    }

    /**
     * The Equipment's toString. Used when playing in the Console.
     * @return Name of the Equipment and uses left of the Equipment.
     */
    @Override
    public String toString() {
        return "Glove: " + usesLeft + " uses left";
    }
}
