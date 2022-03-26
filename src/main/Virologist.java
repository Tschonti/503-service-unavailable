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
    private final Inventory inventory;
    private final ArrayList<Effect> activeEffects;

    public Virologist() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        activeEffects = new ArrayList<>();
        inventory = new Inventory(this);
        Initializer.returnWrite(null);
    }

    public void addEffect(Effect effect) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addEffect",
                OutputObject.generateParamsArray(effect)
        );
        activeEffects.add(effect);
        Initializer.returnWrite(null);
    }

    public void removeEffect(Effect effect) {
        Initializer.functionWrite(
                new OutputObject(this),
                "removeEffect",
                OutputObject.generateParamsArray(effect)
        );

        removeEffect(effect);
        Initializer.returnWrite(null);
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

        activeTile.removeVirologist(this);
        newTile.addVirologist(this);
        Initializer.returnWrite(null);
    }

    public void pickUp() {
        Initializer.functionWrite(
                new OutputObject(this),
                "pickUp",
                null
        );
        activeTile.collectItem(inventory);
        Initializer.returnWrite(null);
    }
    public void craft(GeneticCode code) {
        Initializer.functionWrite(
                new OutputObject(this),
                "craft",
                OutputObject.generateParamsArray(code)
        );
        code.craft(inventory);
        Initializer.returnWrite(null);
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

        Inventory inv = v.getInventory();
        ArrayList<Equipment> equipments = inv.getEquipments();
        ArrayList<String> equipmentsString = new ArrayList<>();
        for (Equipment e : equipments)
            equipmentsString.add(e.toString());
        int result = Initializer.questionListWrite("Which equipment would you like to steal?", equipmentsString).getIndex();
        inventory.steal(inv, equipments.get(result));
        Initializer.returnWrite(null);
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

        getCraftables();
        //TODO:
        Initializer.returnWrite(new OutputObject(new ArrayList<>()));
        return new ArrayList<>();
    }

    private ArrayList<Virologist> getNearbyVirologists() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getNearbyVirologist",
                null
        );

        ArrayList<Virologist> result = activeTile.getPlayers();
        Initializer.returnWrite(new OutputObject(result));

        return result;
    }

    private ArrayList<Virologist> getNearbyVirologistsToStealFrom() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getNearbyVirologistToStealFrom",
                null
        );

        ArrayList<Virologist> result = activeTile.getPlayersToStealFrom();
        Initializer.returnWrite(new OutputObject(result));

        return result;
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

    public static void setController(Controller controller) {
        Initializer.functionWrite(
                new OutputObject("Virologist", true),
                "static setController",
                null
        );
        Virologist.controller = controller;
        Initializer.returnWrite(null);
    }
}
