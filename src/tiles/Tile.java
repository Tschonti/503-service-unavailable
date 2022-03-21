package tiles;

import main.Collectable;
import main.Inventory;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.ArrayList;

public abstract class Tile {
    protected int id;
    protected String name;
    private ArrayList<Tile> neighbours;

    public Tile(int id, String name) {
        //TODO: ez a kiiratás nem kell szerintem
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    public ArrayList<Virologist> getPlayersToStealFrom(){
        Initializer.functionWrite(
                new OutputObject(this),
                "getPlayersToStealFrom",
                null
        );
        //TODO:
        Initializer.returnWrite(new OutputObject(new ArrayList<>()));

        return new ArrayList<>();
    }

    public abstract void collectItem(Inventory inv);

    public abstract Collectable getCollectableItem();

    public void addVirologist(Virologist player) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addVirologist",
                OutputObject.generateParamsArray(player)
        );
        Initializer.returnWrite(null);
    }
    public void removeVirologist(Virologist player) {
        Initializer.functionWrite(
                new OutputObject(this),
                "removeVirologist",
                OutputObject.generateParamsArray(player)
        );
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
}
