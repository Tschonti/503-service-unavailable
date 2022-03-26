package main;

import agents.*;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.ArrayList;

/**
 * GeneticCode
 * It is found in Laboratories and can be collected by Virologists.
 * Only one type of agent can be crafted from a genetic code.
 * A Virologist can win the game by collecting all genetic codes.
 */
public class GeneticCode implements Collectable {
    private Agent agent;
    private ArrayList<Resource> price;

    /**
     * Constructor
     */
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

    /**
     * Clones itself to the inventory it gets
     * @param inv
     */
    public void collect(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collect",
                OutputObject.generateParamsArray(inv)
        );

        // TODO klónózás kéne
        inv.addGeneticCode(new GeneticCode());

        Initializer.returnWrite(null);
    }

    /**
     * Checks, if its agent can be crafted from the resources of the inventory.
     * @param inv
     * @return whether the agent can be crafted
     */
    public boolean isCraftable(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "isCraftable",
                OutputObject.generateParamsArray(inv)
        );

        Initializer.returnWrite(new OutputObject(false));
        return false;
    }

    /**
     * Crafts the agent into the inventory and removes the necessary resources from it.
     * @param inv
     */
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

    /**
     * Getter for agent.
     * @return agent
     */
    public Agent getAgent() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getAgent",
                null
        );
        Initializer.returnWrite(new OutputObject(agent));

        return agent;
    }

    /**
     * Setter for agent.
     * @param agent
     */
    public void setAgent(Agent agent) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setAgent",
                OutputObject.generateParamsArray(agent)
        );

        this.agent = agent;

        Initializer.returnWrite(null);
    }

    /**
     * Getter for price.
     * @return price
     */
    public ArrayList<Resource> getPrice() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getPrice",
                null
        );
        Initializer.returnWrite(new OutputObject(price));

        return price;
    }

    /**
     * Setter for price.
     * @param price
     */
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
