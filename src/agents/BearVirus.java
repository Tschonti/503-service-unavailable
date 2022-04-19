package agents;

import java.util.ArrayList;
import main.SRandom;
import main.Virologist;
import tiles.Tile;

public class BearVirus extends Agent {

    public BearVirus() {
        super(3);
    }

    @Override
    public Agent create() {
        return new BearVirus();
    }

    @Override
    public void onTurnImpact(Virologist to) {
        while (to.getActionsLeft() != 0) {
            ArrayList<Tile> neighbours = to.getActiveTile().getNeighbours();
            to.getActiveTile().destroyCollectable();
            to.moveTo(neighbours.get(SRandom.nextRandom(neighbours.size() - 1)));
        }
    }

    @Override
    public void infect(Virologist to) {
        for (Virologist v : to.getActiveTile().getPlayers()) {
            this.use(null, to);
        }
    }
}
