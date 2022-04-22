package agents;

import main.Virologist;

/**
 * This class is responsible for its effect when it is applied to a Virologist. It is also responsible
 * for its time left in a Virologist's inventory.
 */
public class AmnesiaVirus extends Agent {

    /**
     * AmnesiaVirus constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public AmnesiaVirus() {
        super(1); //Rounds left of the Agent in the Virologist's Inventory
    }

    /**
     * AmnesiaVirus constructor. Calls Abstract super's constructor, and sets roundLeft to rLeft.
     * @param rLeft number of turns until it expires.
     */
    public AmnesiaVirus(int rLeft) {
        super(rLeft); //Rounds left of the Agent as an Effect on a Virologist.
    }

    /**
     * Creates an instance of an AmnesiaVirus.
     * @return AmnesiaVirus
     */
    @Override
    public Agent create() {
        return new AmnesiaVirus();
    }

    /* Effect functions */
    /**
     * This function gets called at the beginning of an affected Virologist's turn.
     * Makes the virologist forget all his learnt GeneticCodes.
     * @param to the Virologist that is affected.
     */
    @Override
    public void onTurnImpact(Virologist to) {
        to.getInventory().getLearntCodes().clear();
    }

    /**
     * The Virus's toString. Used when playing in the Console.
     * @return Name of the virus and rounds left of the virus.
     */
    @Override
    public String toString() {
        return "AmnesiaVirus: " + roundsLeft + " rounds left";
    }
}
