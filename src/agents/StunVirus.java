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
        Initializer.returnWrite(new OutputObject(this));
    }

    public Agent create() {
        Initializer.functionWrite(
                new OutputObject(this),
                "create",
                null
        );
        Initializer.returnWrite(new OutputObject(new StunVirus()));

        return new StunVirus();
    }

    /* Effect functions */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        int aL = to.getActionsLeft();
        for(int i = 0; i < aL; i++)
            to.pass();
        Initializer.returnWrite(null);
    }
}
