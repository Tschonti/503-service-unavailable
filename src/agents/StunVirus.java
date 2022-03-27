package agents;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

public class StunVirus extends Agent {

    public StunVirus() {
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
        Agent newAgent = new StunVirus();
        Initializer.returnWrite(new OutputObject(newAgent));

        return newAgent;
    }

    /* Effect functions */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );

        for(int i = 0; i < to.getActionsLeft(); i++) {
            to.pass();
        }

        Initializer.returnWrite(null);
    }
}
