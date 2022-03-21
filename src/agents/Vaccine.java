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
        Initializer.returnWrite(new OutputObject(this));
    }

    public Agent create() {
        Initializer.functionWrite(
                new OutputObject(this),
                "create",
                null
        );
        Initializer.returnWrite(null);
        return new Vaccine();
    }
    /* Effect functions */
    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                OutputObject.generateParamsArray(agent, from, to)
        );
        Initializer.returnWrite(null);
    }
}
