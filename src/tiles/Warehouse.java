package tiles;

import main.Collectable;
import main.Inventory;
import main.Resource;

public class Warehouse extends Tile {
    private Resource collectable;

    public Warehouse(int id, String name) {
        super(id, name);
    }

    public void collectItem(Inventory inv) {}
    public Collectable getCollectableItem() {
        return collectable;
    }
}
