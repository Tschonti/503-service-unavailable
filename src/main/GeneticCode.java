package main;

import agents.*;

import java.util.ArrayList;

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
     * @param a The agent that can be crafted by learning this code.
     */
    public GeneticCode(Agent a, ArrayList<Resource> p) {
        agent = a;
        price = p;
    }

    /**
     * Clones itself to the inventory it gets.
     * @param inv The inventory, that will get the clone of this genetic code.
     */
    public void collect(Inventory inv) {
        if (!inv.getLearntCodes().contains(this)) {
            inv.addGeneticCode(this);
        }
    }

    public Collectable cloneCollectable() {
        return new GeneticCode(agent.create(), price);
    }

    /**
     * Checks, if its agent can be crafted from the resources of the inventory.
     * @param inv The checked inventory.
     * @return Whether the agent can be crafted.
     */
    public boolean isCraftable(Inventory inv) {
        ArrayList<Resource> invRes = inv.getResources();
        for (Resource res : price) {
            int invAmount = Resource.getResourceByType(invRes, res.getType()).getAmount();
            if (invAmount < res.getAmount()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Crafts the agent into the inventory and removes the necessary resources from it.
     * @param inv The inventory, into which we craft the agent that can be crafted from this genetic code.
     */
    public void craft(Inventory inv) {
        for (Resource res : price) {
            inv.removeResource(res);
        }
        inv.addCraftedAgent(agent.create());
    }

    /**
     * Getter for agent.
     * @return agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Setter for agent.
     * @param agent The new agent.
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Getter for price.
     * @return price
     */
    public ArrayList<Resource> getPrice() {
        return price;
    }

    /**
     * Setter for price.
     * @param price The new price.
     */
    public void setPrice(ArrayList<Resource> price) {
        this.price = price;
    }
}
