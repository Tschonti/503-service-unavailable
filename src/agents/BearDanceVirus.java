package agents;

import java.util.ArrayList;
import main.SRandom;
import main.Virologist;
import tiles.Tile;

/**
 * This class is responsible for its effect when it is applied to a Virologist. It is also responsible
 * for its time left in a Virologist's inventory.
 */
public class BearDanceVirus extends Agent {

    /**
     * StunVirus constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public BearDanceVirus() {
        super(100);
    }

    /**
     * AmnesiaVirus constructor. Calls Abstract super's constructor, and sets roundLeft to rLeft.
     * @param rLeft number of turns until it expires.
     */
    public BearDanceVirus(int rLeft) {
        super(rLeft); //Rounds left of the Agent as an Effect on a Virologist.
    }

    /**
     * Creates an instance of an BearDanceVirus.
     * @return BearDanceVirus
     */
    @Override
    public Agent create() {
        return new BearDanceVirus();
    }

    /**
     * This function gets called at the beginning of an affected Virologist's turn.
     * Makes the virologist move randomly.
     * @param to the Virologist that is affected.
     */
    @Override
    public void onTurnImpact(Virologist to) {
        to.getActiveTile().destroyCollectable();
        while (to.getActionsLeft() > 0) {
            ArrayList<Tile> neighbours = to.getActiveTile().getNeighbours();
            Tile newTile = neighbours.get(SRandom.nextRandom(neighbours.size()));
            to.moveTo(newTile);
            to.getActiveTile().destroyCollectable();
        }
    }

    /**
     * Infects all Virologists on the same tile.
     * @param to The virologist that is infecting.
     */
    @Override
    public void infect(Virologist to) {
        for (Virologist v : to.getNearbyVirologists()) {
            boolean infected = Virologist.getController().isInfected(v);
            if (!infected) {
                this.use(null, v);
                if (v.getActiveEffects().contains(this)) {
                    Virologist.getController().addInfected(v);
                }
            }
        }
    }

    @Override
    public boolean decrement(Virologist v) {
        return false;
    }

    /**
     * The Agent's toString. Used when playing in the Console.
     * @return Name of the agent.
     */
    @Override
    public String toString() {
        return "BearDanceVirus";
    }
}
