package tiles;

import main.Collectable;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

public class EmptyTile extends Tile {
    public EmptyTile(int id, String name) {
        super(id, name);

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }
    public void collectItem(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collectItem",
                OutputObject.generateParamsArray(inv)
        );

        Initializer.returnWrite(null);
    }
    public Collectable getCollectableItem() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCollectableItem",
                null
        );
        Initializer.returnWrite(null);

        return null;
    }
}
