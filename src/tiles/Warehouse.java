package tiles;

import main.Collectable;
import main.Inventory;
import main.Resource;
import skeleton.Initializer;
import skeleton.OutputObject;

public class Warehouse extends Tile {
    private Resource collectable;

    public Warehouse(int id, String name) {
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
        inv.addResource(collectable);
        Initializer.returnWrite(null);
    }
    public Collectable getCollectableItem() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCollectableItem",
                null
        );
        Initializer.returnWrite(new OutputObject(collectable));

        return collectable;
    }
}
