package tiles;

import equipments.Equipment;
import main.Collectable;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

public class Safehouse extends Tile {
    private Equipment equipment;

    public Safehouse(int id, String name) {
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
                null
        );
        Initializer.returnWrite(null);
    }
    public Collectable getCollectableItem() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCollectableItem",
                null
        );
        Initializer.returnWrite(new OutputObject(equipment));

        return equipment;
    }
}
