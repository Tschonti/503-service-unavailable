package agents;

import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

public class Vaccine extends Agent{

    public Vaccine() {
        super(3); //Rounds left of the Agent in the Virologist's Inventory

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

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
