package agents;

import main.Constants;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

import java.util.ArrayList;
import java.util.Random;

public class VitusDanceVirus extends Agent{

    public VitusDanceVirus() {
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
                OutputObject.generateParamsArray()
        );
        Agent newAgent = new VitusDanceVirus();
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
