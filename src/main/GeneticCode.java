package main;

import agents.*;
import observables.ObservableGeneticCode;

import java.util.ArrayList;

/**
 * It is found in Laboratories and can be collected by Virologists.
 * Only one type of agent can be crafted from a genetic code.
 * A Virologist can win the game by collecting all genetic codes.
 */
public class GeneticCode implements Collectable {

    /**
     * Observable for the genetic code.
     */
    private final ObservableGeneticCode obsGeneticCode;

    /**
     * The agent that can be crafted from this genetic code.
     */
    private final Agent agent;

    /**
     * The price of crafting the agent from this genetic code.
     * It stores all the required resources.
     */
    private final ArrayList<Resource> price;

    /**
     * Unique id of the genetic code.
     */
    private final int id;

    /**
     * Constructor
     * @param newId id
     * @param a agent
     * @param p price
     */
    public GeneticCode(int newId, Agent a, ArrayList<Resource> p) {
        obsGeneticCode = new ObservableGeneticCode(this);
        id = newId;
        agent = a;
        price = p;
    }

    /**
     * Getter for ObservableGeneticCode.
     * @return ObservableGeneticCode
     */
    public ObservableGeneticCode getView() {
        return obsGeneticCode;
    }

    /**
     * Puts itself into the inventory it gets.
     * @param inv The inventory, that will get this genetic code.
     */
    public void collect(Inventory inv) {
        for (GeneticCode gc : inv.getLearntCodes()) {
            if (gc.getId() == id) {
                throw new IllegalStateException("You've already learnt this code!");
            }
        }
        inv.addGeneticCode(this);
    }

    /**
     * Clones this genetic code.
     * @return The cloned genetic code.
     */
    public Collectable cloneCollectable() {
        return new GeneticCode(id, agent.create(), price);
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
     * Getter for price.
     * @return price
     */
    public ArrayList<Resource> getPrice() { //TODO remove if not needed
        return price;
    }

    /**
     * Getter for the id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * toString method for genetic code.
     * @return A string from the genetic code.
     */
    @Override
    public String toString() {
        return "GeneticCode #" + id + ": " + agent + ", " + price;
    }
}
