package tiles;

import main.Collectable;
import main.Effect;
import main.Inventory;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.ArrayList;

public abstract class Tile {
    protected int id;
    protected String name;
    private final ArrayList<Tile> neighbours;
    private final ArrayList<Virologist> players;

    public Tile(int id, String name) {
        players = new ArrayList<>();
        neighbours = new ArrayList<>();
    }

    public ArrayList<Virologist> getPlayersToStealFrom(){
        Initializer.functionWrite(
                new OutputObject(this),
                "getPlayersToStealFrom",
                null
        );

        ArrayList<Virologist> result = new ArrayList<>();
        for (Virologist v : players) {
            for (Effect e : v.getActiveEffects()) {
                e.allowStealing();
            }
        }

        //TODO:
        Initializer.returnWrite(new OutputObject(result));
        return result;
    }

    public abstract void collectItem(Inventory inv);

    public abstract Collectable getCollectableItem();

    public void addVirologist(Virologist player) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addVirologist",
                OutputObject.generateParamsArray(player)
        );
        players.add(player);
        Initializer.returnWrite(null);
    }
    public void removeVirologist(Virologist player) {
        Initializer.functionWrite(
                new OutputObject(this),
                "removeVirologist",
                OutputObject.generateParamsArray(player)
        );
        players.remove(player);
        Initializer.returnWrite(null);
    }

    public int getId() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getId",
                null
        );
        Initializer.returnWrite(new OutputObject(id));

        return id;
    }

    public String getName() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getName",
                null
        );
        Initializer.returnWrite(new OutputObject(name));

        return name;
    }

    public ArrayList<Tile> getNeighbours() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getNeighbours",
                null
        );
        Initializer.returnWrite(new OutputObject(neighbours));

        return neighbours;
    }

    public void addNeighbour(Tile t) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addNeighbour",
                OutputObject.generateParamsArray(t)
        );
        Initializer.returnWrite(null);

        neighbours.add(t);
    }

    public ArrayList<Virologist> getPlayers() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getPlayers",
                null
        );
        Initializer.returnWrite(new OutputObject(players));

        return players;
    }
}
