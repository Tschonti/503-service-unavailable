package agents;

import main.Constants;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

import java.util.ArrayList;
import java.util.Random;

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
        Initializer.returnWrite(new OutputObject(this));
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
        Initializer.returnWrite(new OutputObject(new VitusDanceVirus()));

        return new VitusDanceVirus();
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
        for(int i = 0; i < Constants.numberOfActions; i++) {
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
