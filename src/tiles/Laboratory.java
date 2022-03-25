package tiles;

import main.Collectable;
import main.GeneticCode;
import main.Inventory;
import skeleton.Initializer;
import skeleton.OutputObject;

public class Laboratory extends Tile {
    private GeneticCode code;

    public Laboratory(int id, String name) {
        super(id, name);

        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        code = new GeneticCode();
        Initializer.returnWrite(null);
    }

    public void collectItem(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collectItem",
                null
        );
        inv.addGeneticCode(code);
        Initializer.returnWrite(null);
    }

    public Collectable getCollectableItem() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCollectableItem",
                null
        );
        Initializer.returnWrite(new OutputObject(code));

        return code;
    }
}
