package equipments;
import main.Inventory;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

public class Bag extends Equipment {

    public Bag() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(new OutputObject(this));
    }

    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Inventory i=to.getInventory();
        int amount=i.getMaxResourceAmount();
        i.setMaxResourceAmount(2*amount);
        Initializer.returnWrite(null);
    }

    public void endTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "endTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Inventory i=to.getInventory();
        int amount=i.getMaxResourceAmount();
        i.setMaxResourceAmount(amount/2);
        Initializer.returnWrite(null);
    }

    protected Equipment clone() {
        Initializer.functionWrite(
                new OutputObject(this),
                "clone",
                null
        );
        Initializer.returnWrite(new OutputObject(new Bag()));

        return new Bag();
    }
}
