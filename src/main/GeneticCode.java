package main;

import agents.Agent;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.ArrayList;

public class GeneticCode implements Collectable {
    private Agent agent;
    private ArrayList<Resource> price;

    public GeneticCode() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    public void collect(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collect",
                OutputObject.generateParamsArray(inv)
        );
        Initializer.returnWrite(null);
    }

    public boolean isCraftable(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "isCraftable",
                OutputObject.generateParamsArray(inv)
        );
        Initializer.returnWrite(new OutputObject(false));

        return false;
    }

    public void craft(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "craft",
                OutputObject.generateParamsArray(inv)
        );
        Initializer.returnWrite(null);
    }

    public Agent getAgent() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getAgent",
                null
        );
        Initializer.returnWrite(new OutputObject(agent));

        return agent;
    }

    public void setAgent(Agent agent) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setAgent",
                OutputObject.generateParamsArray(agent)
        );
        Initializer.returnWrite(null);

        this.agent = agent;
    }

    public ArrayList<Resource> getPrice() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getPrice",
                null
        );
        Initializer.returnWrite(new OutputObject(price));

        return price;
    }

    public void setPrice(ArrayList<Resource> price) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setPrice",
                OutputObject.generateParamsArray(price)
        );
        Initializer.returnWrite(null);

        this.price = price;
    }
}
