package agents;
import main.Inventory;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * This class is responsible for it's effect when it is applied to a Virologist. It is also responsible
 * for it's time left.
 */
public class AmnesiaVirus extends Agent {

    /**
     * AmnesiaVirus constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public AmnesiaVirus() {
        super(3); //Rounds left of the Agent in the Virologist's Inventory

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    /**
     * AmnesiaVirus constructor. Calls Abstract super's constructor, and sets roundLeft to rLeft.
     * @param rLeft number of turns until it expires.
     */
    public AmnesiaVirus(int rLeft) {
        super(rLeft); //Rounds left of the Agent as an Effect on a Virologist.
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    /**
     * Creates an instance of an AmnesiaVirus
     * @return AmnesiaVirus
     */
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
    /**
     * This function gets called at the beginning of an affected Virologist's turn.
     * Makes the virologist forget all his learnt GeneticCodes.
     * @param to the Virologist that is affected.
     */
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
