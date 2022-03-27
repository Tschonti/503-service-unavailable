package agents;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

public class StunVirus extends Agent {
    /**
     * StunVirus constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public StunVirus() {
        super(3); //Rounds left of the Agent in the Virologist's Inventory

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(new OutputObject(this));
    }

    /**
     * Creates an instance of a StunVirus
     * @return StunVirus
     */
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
    /**
     * This function gets called at the beginning of an affected Virologist's turn.
     * Makes the virologist miss his/her turn.
     * @param to the Virologist that is affected.
     */
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
