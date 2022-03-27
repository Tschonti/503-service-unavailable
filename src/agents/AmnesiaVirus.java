package agents;
import main.GeneticCode;
import main.Inventory;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

public class AmnesiaVirus extends Agent {

    public AmnesiaVirus() {
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
        Agent newAgent = new AmnesiaVirus();
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
        Inventory inv = to.getInventory();
        inv.getGeneticCodes().clear();
        Initializer.returnWrite(null);
    }
}
