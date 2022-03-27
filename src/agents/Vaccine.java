package agents;

import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * This class is responsible for its effect when it is applied to a Virologist. It is also responsible
 * for it's time left.
 */
public class Vaccine extends Agent {
    /**
     * Vaccine constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public Vaccine() {
        super(3); //Rounds left of the Agent in the Virologist's Inventory

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    /**
     * Vaccine constructor. Calls Abstract super's constructor, and sets roundLeft to rLeft.
     * @param rLeft number of turns until it expires.
     */
    public Vaccine(int rLeft) {
        super(rLeft); //Rounds left of the Agent as an Effect on a Virologist.
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    /**
     * Creates an instance of a Vaccine
     * @return Vaccine
     */
    public Agent create() {
        Initializer.functionWrite(
                new OutputObject(this),
                "create",
                null
        );
        Agent newAgent = new Vaccine();
        Initializer.returnWrite(new OutputObject(newAgent));

        return newAgent;
    }

    /* Effect functions */
    /**
     * Gets called, when a Virologist uses an Agent on another Virologist that has the Vaccine Effect.
     * Stops the affected Virologist from getting infected.
     * @param agent the new Effect
     * @param from the Virologist that is trying to infect.
     * @param to the Virologist that is being infected.
     */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                OutputObject.generateParamsArray(agent, from, to)
        );
        to.removeEffect(agent);
        Initializer.returnWrite(null);
    }
}
