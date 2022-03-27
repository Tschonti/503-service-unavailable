package agents;

import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is responsible for it's effect when it is applied to a Virologist. It is also responsible
 * for it's time left.
 */
public class VitusDanceVirus extends Agent{
    /**
     * VitusDanceVirus constructor. Calls Abstract super's constructor, and sets roundLeft to 3.
     */
    public VitusDanceVirus() {
        super(3); //Rounds left of the Agent in the Virologist's Inventory

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    /**
     * VitusDanceVirus constructor. Calls Abstract super's constructor, and sets roundLeft to rLeft.
     * @param rLeft number of turns until it expires.
     */
    public VitusDanceVirus(int rLeft) {
        super(rLeft); //Rounds left of the Agent as an Effect on a Virologist.
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    /**
     * Creates an instance of a VitusDanceVirus
     * @return VitusDanceVirus
     */
    public Agent create() {
        Initializer.functionWrite(
                new OutputObject(this),
                "create",
                OutputObject.generateParamsArray()
        );
        Agent newAgent = new VitusDanceVirus();
        Initializer.returnWrite(new OutputObject(newAgent));

        return newAgent;
    }

    /* Effect functions */
    /**
     * This function gets called at the beginning of an affected Virologist's turn.
     * Makes the virologist move randomly.
     * @param to the Virologist that is affected.
     */
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        int actionsLeft = to.getActionsLeft();
        for(int i = 0; i < actionsLeft; i++) {
            Tile t = to.getActiveTile();
            ArrayList<Tile> tileList = t.getNeighbours();
            Random r = new Random();
            int randomInt = tileList.size() == 1 ? 0 : r.nextInt(tileList.size() - 1);
            Tile randomTile = tileList.get(randomInt);
            to.moveTo(randomTile);
        }
        Initializer.returnWrite(null);
    }
}
