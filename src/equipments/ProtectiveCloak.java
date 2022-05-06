package equipments;

import agents.Agent;
import main.Collectable;
import main.SRandom;
import main.Virologist;

/**
 * An Equipment, saves a thrown Virologist from the Effect with 82.3% chance.
 */
public class ProtectiveCloak extends Equipment {

    /**
     * Constructor
     */
    public ProtectiveCloak() {
        super();
    }

    /**
     * The ProtectiveCloaks impact in case of a Virologist throws an Agent on another Virologist.
     * It has 82.3% chance of removing the agent from the thrown Virologists effects.
     * @param agent The used Agent.
     * @param from The throwing Virologist.
     * @param to The Virologist, who gets thrown.
     */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        if (SRandom.nextRandom(1000) < 823) {
            to.removeEffect(agent);
        }
    }

    /**
     * Creates a new ProtectiveCloak and returns it.
     * @return The new ProtectiveCloak.
     */
    public Collectable cloneCollectable() {
        return new ProtectiveCloak();
    }

    /**
     * The Equipment's toString. Used when playing in the Console.
     * @return Name of the Equipment.
     */
    @Override
    public String toString() {
        return "ProtectiveCloak";
    }
}
