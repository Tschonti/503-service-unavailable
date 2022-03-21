package equipments;
import agents.Agent;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;


public class Glove extends Equipment {
    public void counterImpact(Agent agent, Virologist from, Virologist to) {
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                OutputObject.generateParamsArray(agent, from, to)
        );
        Initializer.returnWrite(null);
    }
    protected Equipment clone() {
        Initializer.functionWrite(
                new OutputObject(this),
                "clone",
                null
        );
        Initializer.returnWrite(null);
        return new Glove();
    }
}
