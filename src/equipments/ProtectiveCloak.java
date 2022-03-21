package equipments;
import agents.Agent;
import main.Virologist;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.Random;

public class ProtectiveCloak extends Equipment {
    public void counterImpact(Agent agent, Virologist from, Virologist to){
        //int i = (int)(Math.random()*1000);
        //if(i<=823){
        //    //print
        //    to.removeEffect(agent);
        //}
        Initializer.functionWrite(
                new OutputObject(this),
                "counterImpact",
                null
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
        return new ProtectiveCloak();
    }
}
