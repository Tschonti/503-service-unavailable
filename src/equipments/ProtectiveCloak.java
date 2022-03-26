package equipments;
import agents.Agent;
import main.Collectable;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.Random;

public class ProtectiveCloak extends Equipment {

    public ProtectiveCloak() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(new OutputObject(this));
    }

    public void counterImpact(Agent agent, Virologist from, Virologist to){
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                null
        );
        boolean q=Initializer.questionWrite("Do you want the ProtectiveCloak to save its owner?");
        if(q){
            to.removeEffect(agent);
        }
        Initializer.returnWrite(null);


    }

    public Collectable clone() {
        Initializer.functionWrite(
                new OutputObject(this),
                "clone",
                null
        );
        Initializer.returnWrite(new OutputObject(new ProtectiveCloak()));

        return new ProtectiveCloak();
    }
}
