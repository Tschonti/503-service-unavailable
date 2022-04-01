package main;

import agents.*;
import java.util.ArrayList;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * It is found in Laboratories and can be collected by Virologists.
 * Only one type of agent can be crafted from a genetic code.
 * A Virologist can win the game by collecting all genetic codes.
 */
public class GeneticCode implements Collectable {

    /**
     * The agent that can be crafted from this genetic code.
     */
    private Agent agent;

    /**
     * The price of crafting the agent from this genetic code.
     * It stores all the required resources.
     */
    private ArrayList<Resource> price;

    /**
     * Constructor
     * The type of the agent is random.
     */
    public GeneticCode() {
        Initializer.functionWrite(new OutputObject(this), "constructor", null);

        int random = (int) (Math.random() * 10);
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
        price.add(new Resource(10, ResourceType.AminoAcid));
        Initializer.returnWrite(null);
    }

    /**
     * Constructor
     * @param a The agent that can be crafted by learning this code.
     */
    public GeneticCode(Agent a) {
        Initializer.functionWrite(
            new OutputObject(this),
            "constructor",
            OutputObject.generateParamsArray(a)
        );

        agent = a;
        price = new ArrayList<>();

        Initializer.returnWrite(null);
    }

    /**
     * Clones itself to the inventory it gets.
     * @param inv The inventory, that will get the clone of this genetic code.
     */
    public void collect(Inventory inv) {
        Initializer.functionWrite(
            new OutputObject(this),
            "collect",
            OutputObject.generateParamsArray(inv)
        );
        inv.addGeneticCode(this);
        Initializer.returnWrite(null);
    }

    public Collectable cloneCollectable() {
        Initializer.functionWrite(new OutputObject(this), "cloneCollectable", null);

        GeneticCode newGc = new GeneticCode(agent.create());
        newGc.setPrice(price);
        Initializer.returnWrite(new OutputObject(newGc));
        return newGc;
    }

    /**
     * Checks, if its agent can be crafted from the resources of the inventory.
     * @param inv The checked inventory.
     * @return Whether the agent can be crafted.
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
     * @param inv The inventory, into which we craft the agent that can be crafted from this genetic code.
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
        Initializer.functionWrite(new OutputObject(this), "getAgent", null);
        Initializer.returnWrite(new OutputObject(agent));

        return agent;
    }

    /**
     * Setter for agent.
     * @param agent The new agent.
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
        Initializer.functionWrite(new OutputObject(this), "getPrice", null);
        Initializer.returnWrite(new OutputObject(price));

        return price;
    }

    /**
     * Setter for price.
     * @param price The new price.
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
