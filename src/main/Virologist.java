package main;

import agents.Agent;
import equipments.Equipment;
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

    public Virologist() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        Initializer.returnWrite(null);
    }

    public void addEffect(Effect effect) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addEffect",
                OutputObject.generateParamsArray(effect)
        );
        Initializer.returnWrite(null);

        useAgent((Agent)effect, this);
        activeEffects.add(effect);
    }

    public void removeEffect(Effect effect) {
        Initializer.functionWrite(
                new OutputObject(this),
                "removeEffect",
                OutputObject.generateParamsArray(effect)
        );
        Initializer.returnWrite(null);

        removeEffect(effect);
    }

    public void pass() {
        Initializer.functionWrite(
                new OutputObject(this),
                "pass",
                null
        );
        Initializer.returnWrite(null);
    }

    public void myTurn() {
        Initializer.functionWrite(
                new OutputObject(this),
                "myTurn",
                null
        );
        Initializer.returnWrite(null);
    }

    private void moveTo(Tile newTile) {
        Initializer.functionWrite(
                new OutputObject(this),
                "moveTo",
                OutputObject.generateParamsArray(newTile)
        );
        Initializer.returnWrite(null);

        activeTile.removeVirologist(this);
        newTile.addVirologist(this);
    }

    public void pickUp() {
        Initializer.functionWrite(
                new OutputObject(this),
                "pickUp",
                null
        );
        Initializer.returnWrite(null);

        activeTile.collectItem(inventory);
    }
    public void craft(GeneticCode code) {
        Initializer.functionWrite(
                new OutputObject(this),
                "craft",
                OutputObject.generateParamsArray(code)
        );
        Initializer.returnWrite(null);

        code.craft(inventory);
    }

    public void useAgent(Agent agent, Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "useAgent",
                OutputObject.generateParamsArray(agent, v)
        );
        Initializer.returnWrite(null);

    }

    public void steal(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "steal",
                OutputObject.generateParamsArray(v)
        );
        Initializer.returnWrite(null);

        Inventory inv = v.getInventory();
        ArrayList<Equipment> equipments = inv.getEquipments();
        ArrayList<String> equipmentsString = new ArrayList<>();
        for (Equipment e : equipments)
            equipmentsString.add(e.toString());
        int result = Initializer.questionListWrite("Which equipment would you like to steal?", equipmentsString);
        inventory.steal(inv, equipments.get(result));

    }

    private ArrayList<Tile> getNeighbours() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getNeighbours",
                null
        );
        Initializer.returnWrite(new OutputObject(activeTile.getNeighbours()));

        return activeTile.getNeighbours();
    }
    private ArrayList<GeneticCode> getCraftables() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCraftables",
                null
        );
        //TODO:
        Initializer.returnWrite(new OutputObject(new ArrayList<>()));

        return new ArrayList<>();
    }
    private ArrayList<Agent> getCraftedAgents() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCraftedAgents",
                null
        );
        //TODO:
        Initializer.returnWrite(new OutputObject(new ArrayList<>()));

        getCraftables();
        return new ArrayList<>();
    }

    private ArrayList<Virologist> getNearbyVirologists() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getNearbyVirologist",
                null
        );
        //TODO:
        Initializer.returnWrite(new OutputObject(new ArrayList<>()));

        return new ArrayList<>();
    }

    private ArrayList<Virologist> getNearbyVirologistsToStealFrom() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getNearbyVirologistToStealFrom",
                null
        );
        //TODO:
        Initializer.returnWrite(new OutputObject(new ArrayList<>()));

        return new ArrayList<>();
    }

    public int getActionsLeft() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getActionsLeft",
                null
        );
        Initializer.returnWrite(new OutputObject(actionsLeft));

        return actionsLeft;
    }

    public Tile getActiveTile() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getActiveTile",
                null
        );
        Initializer.returnWrite(new OutputObject(activeTile));

        return activeTile;
    }

    public void setActiveTile(Tile activeTile) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setActiveTile",
                OutputObject.generateParamsArray(activeTile)
        );
        Initializer.returnWrite(null);

        this.activeTile = activeTile;
    }

    public Inventory getInventory() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getInventory",
                null
        );
        Initializer.returnWrite(new OutputObject(inventory));

        return inventory;
    }


    public ArrayList<Effect> getActiveEffects() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getActiveEffects",
                null
        );
        Initializer.returnWrite(new OutputObject(activeEffects));

        return activeEffects;
    }
}
