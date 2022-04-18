package tiles;

import agents.Agent;
import main.GeneticCode;
import main.Virologist;

public class InfectedLaboratory extends Laboratory {

    /**
     * The Agent that infects everyone who moves to this tile.
     */
    private final Agent infectable;

    /**
     * Constructor
     * @param id Unique identifier of the tile
     * @param name Name of the tile
     * @param gc The GeneticCode that can be learnt here
     * @param infect The Agent that infects everyone who moves to this tile
     */
    public InfectedLaboratory(int id, String name, GeneticCode gc, Agent infect) {
        super(id, name, gc);
        infectable = infect;
    }

    /**
     * Adds the virologist to the players list.
     * Called when a virologist moves to this field.
     * Infects the player that moved here with the agent of the tile.
     * @param player The player to be added.
     */
    public void addVirologist(Virologist player) {
        players.add(player);
        infectable.use(null, player);
    }
}
