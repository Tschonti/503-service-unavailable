package tiles;

import main.Collectable;
import main.Inventory;
import main.Virologist;

import java.util.ArrayList;

public abstract class Tile {
    protected int id;
    protected String name;
    private ArrayList<Tile> neighbours;

    public Tile(int id, String name) {}

    public ArrayList<Virologist> getPlayersToStealFrom(){
        return new ArrayList<>();
    }

    public abstract void collectItem(Inventory inv);
    public abstract Collectable getCollectableItem();
    public void addVirologist(Virologist player) {}
    public void removeVirologist(Virologist player) {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tile> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Tile t) {
        neighbours.add(t);
    }
}
