package tiles;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import main.Collectable;
import main.Effect;
import main.Inventory;
import main.Virologist;
import observables.ObservableTileName;
import observables.ObservableTileType;

/**
 * Abstract class for the fields of the game.
 * Keeps track of the players that are currently on the tile.
 * Players can pick up various items here.
 */
public abstract class Tile {

    /**
     * Unique identifier of the tile.
     */
    protected final int id;
    /**
     * Name of the tile.
     */
    protected final String name;
    /**
     * List of neighbouring tiles.
     */
    private final ArrayList<Tile> neighbours;
    /**
     * List of players that are currently on this tile.
     */
    protected final ArrayList<Virologist> players;

    /**
     * The collectable that can be picked up from this tile.
     */
    protected Collectable collectable = null;

    private final ObservableTileName nameView;
    private final ObservableTileType typeView;

    /**
     * Constructor
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     */
    public Tile(int id, String name, Color c) {
        this.id = id;
        this.name = name;
        players = new ArrayList<>();
        neighbours = new ArrayList<>();
        nameView = new ObservableTileName(this, c);
        typeView = new ObservableTileType(this, c);
    }

    /**
     * Constructor that also sets the Collectable
     * @param id Unique identifier of the tile.
     * @param name Name of the tile.
     * @param c The collectable that can be picked up from this tile
     */
    public Tile(int id, String name, Collectable c, Color col) {
        this(id, name, col);
        collectable = c;
    }

    /**
     * Return the virologist from players that have an effect applied to them that allows stealing.
     * @return Virologists that can be robbed.
     */
    public List<Virologist> getPlayersToStealFrom() {
        return players
            .stream()
            .filter(player -> player.getActiveEffects().stream().anyMatch(Effect::allowStealing))
            .collect(Collectors.toList());
    }

    /**
     * Clones the collectable of the field and calls its collect method,
     * that'll eventually put the new collectable into the inventory.
     * @param inv The clone of the Collectable has to be stored in this inventory.
     */
    public void collectItem(Inventory inv) {
        if (collectable != null) {
            collectable.cloneCollectable().collect(inv);
        }
    }

    /**
     * Getter for the collectable of the field.
     * @return Collectable of the field.
     */
    public Collectable getCollectableItem() {
        return collectable;
    }

    /**
     * Adds the virologist to the players list.
     * Called when a virologist moves to this field.
     * Calls infect on all the effects of all the players on this Tile.
     * @param player The player to be added.
     */
    public void addVirologist(Virologist player) {
        players.add(player);
        ArrayList<Virologist> virologists = new ArrayList<>(player.getActiveTile().getPlayers());
        virologists.forEach(p -> p.getActiveEffects().forEach(e -> e.infect(p)));
    }

    /**
     * Removes the virologist from the players list.
     * Called when a virologist moves from this field.
     * @param player The player to be removed.
     */
    public void removeVirologist(Virologist player) {
        players.remove(player);
    }

    /**
     * A method to destroy the collectable of the tile.
     * Here it does nothing, so only those classes have to
     * implement it that actually use it.
     */
    public void destroyCollectable() {}

    /**
     * Getter for the name.
     * @return Name of the tile.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the neighbours list.
     * @return List of neighbouring tiles.
     */
    public ArrayList<Tile> getNeighbours() {
        return neighbours;
    }

    public ObservableTileName getNameView() {
        return nameView;
    }

    public ObservableTileType getTypeView() {
        return typeView;
    }

    /**
     * Adds a tile as a neighbour to this tile.
     * @param t Tile to be added as neighbour.
     */
    public void addNeighbour(Tile t) {
        neighbours.add(t);
    }

    /**
     * Returns the list of players that are currently on this tile.
     * @return  List of players on this tile.
     */
    public ArrayList<Virologist> getPlayers() {
        return players;
    }

    public String toString() {
        return name;
    }
}
