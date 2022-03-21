package tiles;

import equipments.Equipment;
import main.Collectable;
import main.Inventory;

public class Safehouse extends Tile {
    private Equipment equipment;

    public Safehouse(int id, String name) {
        super(id, name);
    }

    public void collectItem(Inventory inv) {}
    public Collectable getCollectableItem() {
        return equipment;
    }
}
