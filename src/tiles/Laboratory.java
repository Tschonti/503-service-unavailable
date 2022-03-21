package tiles;

import main.Collectable;
import main.GeneticCode;
import main.Inventory;

public class Laboratory extends Tile {
    private GeneticCode code;

    public Laboratory(int id, String name) {
        super(id, name);
    }

    public void collectItem(Inventory inv) {}
    public Collectable getCollectableItem() {
        return code;
    }
}
