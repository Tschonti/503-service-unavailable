package equipments;
import agents.Agent;
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
        Initializer.returnWrite(null);

        //int i = (int)(Math.random()*1000);
        //if(i<=823){
        //    //print
        //    to.removeEffect(agent);
        //}
    }

    protected Equipment clone() {
        Initializer.functionWrite(
                new OutputObject(this),
                "clone",
                null
        );
        Initializer.returnWrite(new OutputObject(new ProtectiveCloak()));

        return new ProtectiveCloak();
    }
}
