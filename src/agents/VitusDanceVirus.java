package agents;

import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

public class VitusDanceVirus extends Agent{

    public VitusDanceVirus() {
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
                OutputObject.generateParamsArray()
        );
        Initializer.returnWrite(null);
        return new VitusDanceVirus();
    }
    /* Effect functions */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }
}
