package main;

import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

public class Map {
    private Tile[] tiles;

    public Map() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    //TODO 4.4.1 szerint geneticCodeokat kéne visszaadnia, de usecaseben úgysincs
    public void createMap() {
        Initializer.functionWrite(
                new OutputObject(this),
                "createMap",
                null
        );
        Initializer.returnWrite(null);
    }
}
