package equipments;
import agents.Agent;
import main.Collectable;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;


public class Glove extends Equipment {

    public Glove() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(new OutputObject(this));
    }

    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                OutputObject.generateParamsArray(agent, from, to)
        );
        to.removeEffect(agent);
        from.addEffect(agent);
        Initializer.returnWrite(null);
    }

    public Collectable clone() {
        Initializer.functionWrite(
                new OutputObject(this),
                "clone",
                null
        );
        Initializer.returnWrite(new OutputObject(new Glove()));

        return new Glove();
    }
}
