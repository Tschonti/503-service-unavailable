package main;

import agents.*;
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

        //TODO random for now
        int random = (int)(Math.random()*10);
        if (random < 3) {
            agent = new StunVirus();
        } else if (random < 6) {
            agent = new AmnesiaVirus();
        } else if (random < 8) {
            agent = new VitusDanceVirus();
        } else {
            agent = new Vaccine();
        }
        price = new ArrayList<>();

        Initializer.returnWrite(null);
    }

    public void collect(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collect",
                OutputObject.generateParamsArray(inv)
        );

        inv.addGeneticCode(new GeneticCode());

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

        for (Resource res : price) {
            inv.removeResource(res);
        }
        inv.addCraftedAgent(agent.create());

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

        this.agent = agent;

        Initializer.returnWrite(null);
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
        this.price = price;
        Initializer.returnWrite(null);
    }
}
