package main;

import agents.Agent;

import java.util.ArrayList;

public class GeneticCode implements Collectable {
    private Agent agent;
    private ArrayList<Resource> price;

    public GeneticCode() {

    }

    public void collect(Inventory inv) {

    }

    public boolean isCraftable(Inventory inv) {
        return false;
    }

    public void craft(Inventory inv) {

    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public ArrayList<Resource> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Resource> price) {
        this.price = price;
    }
}
