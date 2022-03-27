package main;

import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

/**
 * The map of the game.
 * It can create a map and store its tiles.
 */
public class Map {
    /**
     * Tiles of the map.
     */
    private Tile[] tiles;

    /**
     * Constructor
     */
    public Map() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    /**
     * Creates a new map.
     */
    public void createMap() {
        Initializer.functionWrite(
                new OutputObject(this),
                "createMap",
                null
        );
        Initializer.returnWrite(null);
    }
}
