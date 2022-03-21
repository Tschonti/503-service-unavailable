package main;

import agents.Agent;
import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

import java.util.ArrayList;

public class Virologist {
    private int actionsLeft;
    private static Controller controller;
    private Tile activeTile;
    private Inventory inventory;
    private ArrayList<Effect> activeEffects;

    public Virologist() {}

    public void addEffect(Effect effect) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addEffect",
                OutputObject.generateParamsArray(effect)
        );
        //activeEffects.add(effect);

        useAgent((Agent)effect, this);

        Initializer.returnWrite(new OutputObject(this));    // itt null lesz, ez csak példa !!! #TODO
    }

    public void removeEffect(Effect effect) {

    }

    public void pass() {

    }

    public void myTurn() {

    }

    private void moveTo(Tile newTile) {}
    private void pickUp() {}
    private void craft(GeneticCode code) {}

    private void useAgent(Agent agent, Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "useAgent",
                OutputObject.generateParamsArray(agent, v)
        );

        Initializer.returnWrite(null);
    }

    private void steal(Virologist v) {}
    private ArrayList<Tile> getNeighbours() {
        return new ArrayList<>();
    }
    private ArrayList<GeneticCode> getCraftables() {
        return new ArrayList<>();
    }
    private ArrayList<Agent> getCraftedAgents() {
        getCraftables();
        return new ArrayList<>();
    }

    private ArrayList<Virologist> getNearbyVirologists() {
        return new ArrayList<>();
    }

    private ArrayList<Virologist> getNearbyVirologistsToStealFrom() {
        return new ArrayList<>();
    }

    public int getActionsLeft() {
        return actionsLeft;
    }

    public Tile getActiveTile() {
        return activeTile;
    }

    public void setActiveTile(Tile activeTile) {
        this.activeTile = activeTile;
    }

    public Inventory getInventory() {
        return inventory;
    }


    public ArrayList<Effect> getActiveEffects() {
        return activeEffects;
    }
}
