package equipments;

import agents.Agent;
import main.Collectable;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * An Equipment, saves a thrown Virologist from the Effect with 82.3% chance.
 */
public class ProtectiveCloak extends Equipment {

    /**
     * Constructor
     */
    public ProtectiveCloak() {
        Initializer.functionWrite(new OutputObject(this), "constructor", null);
        Initializer.returnWrite(null);
    }

    /**
     * The ProtectiveCloaks impact in case of a Virologist throws an Agent on another Virologist.
     * It has 82.3% chance of removing the agent from the thrown Virologists effects.
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
        boolean saved = Initializer.questionYesOrNo(
            "Do you want the ProtectiveCloak to save its owner?"
        );
        if (saved) {
            to.removeEffect(agent);
        }
        Initializer.returnWrite(null);
    }

    /**
     * Creates a new ProtectiveCloak and returns it.
     * @return The new ProtectiveCloak.
     */
    public Collectable cloneCollectable() {
        Initializer.functionWrite(new OutputObject(this), "clone", null);
        Collectable newCloak = new ProtectiveCloak();
        Initializer.returnWrite(new OutputObject(newCloak));
        return newCloak;
    }
}
