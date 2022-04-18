package agents;

import main.Virologist;

/**
 * This class is responsible for its effect when it is applied to a Virologist. It is also responsible
 * for its time left in a Virologist's inventory.
 */
public class StunVirus extends Agent {

    /**
     * StunVirus constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public StunVirus() {
        super(3); //Rounds left of the Agent in the Virologist's Inventory.
    }

    /**
     * StunVirus constructor. Calls Abstract super's constructor, and sets roundLeft to rLeft.
     * @param rLeft number of turns until it expires.
     */
    public StunVirus(int rLeft) {
        super(rLeft); //Rounds left of the Agent as an Effect on a Virologist.
    }

    /**
     * Creates an instance of a StunVirus.
     * @return StunVirus
     */
    public Agent create() {
        return new StunVirus();
    }

    /* Effect functions */
    /**
     * This function gets called at the beginning of an affected Virologist's turn.
     * Makes the virologist miss his/her turn.
     * @param to the Virologist that is affected.
     */
    public void onTurnImpact(Virologist to) {
        while (to.getActionsLeft() > 0) {
            to.pass();
        }
    }
    @Override
    public boolean allowStealing() {
        return true;
    }
}
