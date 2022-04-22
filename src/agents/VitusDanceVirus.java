package agents;

import java.util.ArrayList;
import main.SRandom;
import main.Virologist;
import tiles.Tile;

/**
 * This class is responsible for its effect when it is applied to a Virologist. It is also responsible
 * for it's time left in a Virologist's inventory.
 */
public class VitusDanceVirus extends Agent {

    /**
     * VitusDanceVirus constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public VitusDanceVirus() {
        super(3); //Rounds left of the Agent in the Virologist's Inventory
    }

    /**
     * VitusDanceVirus constructor. Calls Abstract super's constructor, and sets roundLeft to rLeft.
     * @param rLeft number of turns until it expires.
     */
    public VitusDanceVirus(int rLeft) {
        super(rLeft); //Rounds left of the Agent as an Effect on a Virologist.
    }

    /**
     * Creates an instance of a VitusDanceVirus.
     * @return VitusDanceVirus
     */
    @Override
    public Agent create() {
        return new VitusDanceVirus();
    }

    /* Effect functions */
    /**
     * This function gets called at the beginning of an affected Virologist's turn.
     * Makes the virologist move randomly.
     * @param to the Virologist that is affected.
     */
    @Override
    public void onTurnImpact(Virologist to) {
        while (to.getActionsLeft() > 0) {
            ArrayList<Tile> neighbours = to.getActiveTile().getNeighbours();
            to.moveTo(neighbours.get(SRandom.nextRandom(neighbours.size() - 1)));
        }
    }

    /**
     * The Agent's toString. Used when playing in the Console.
     * @return Name of the virus and rounds left of the virus.
     */
    @Override
    public String toString() {
        return "VitusDanceVirus: " + roundsLeft + " rounds left";
    }
}