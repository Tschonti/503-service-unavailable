package equipments;
import main.Effect;
import main.Collectable;
import main.Virologist;
import main.Inventory;
import agents.Agent;
import skeleton.Initializer;
import skeleton.OutputObject;

public abstract class Equipment implements Effect, Collectable {

    public void collect(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collect",
                OutputObject.generateParamsArray(inv)
        );
        Equipment cloned=this.clone();
        inv.addEquipment(cloned);
        Initializer.returnWrite(null);
    }

    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                OutputObject.generateParamsArray(agent, from, to)
        );
        Initializer.returnWrite(null);
    }

    public void onTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "onTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    public void endTurnImpact(Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "endTurnImpact",
                OutputObject.generateParamsArray(to)
        );
        Initializer.returnWrite(null);
    }

    public boolean allowStealing() {
        Initializer.functionWrite(
                new OutputObject(this),
                "allowStealing",
                null
        );
        Initializer.returnWrite(new OutputObject(false));
        return false;
    }

    public void decrement(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "decrement",
                OutputObject.generateParamsArray(v)
        );
        Initializer.returnWrite(null);
    }

    protected abstract Equipment clone();
}
