package agents;

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
        Initializer.returnWrite(new OutputObject(this));
    }

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
    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        for(int i = 0; i < 2; i++) {
            Tile t = to.getActiveTile();
            ArrayList<Tile> tileList = t.getNeighbours();
            Random r = new Random();
            int randomInt = tileList.size() == 1 ? 0 : r.nextInt(tileList.size() - 1);
            Tile rT = tileList.get(randomInt);
            to.moveTo(rT);
            t.removeVirologist(to);
            rT.addVirologist(to);
        }
        Initializer.returnWrite(null);
    }
}
