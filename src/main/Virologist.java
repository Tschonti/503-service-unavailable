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
    private String name = "";

    /**
     *
     * @param name
     */
    public Virologist(String name) {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );
        activeEffects = new ArrayList<>();
        inventory = new Inventory(this);
        this.name = name;
        Initializer.returnWrite(null);
    }

    /**
     *
     * @param effect
     */
    public void addEffect(Effect effect) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addEffect",
                OutputObject.generateParamsArray(effect)
        );
        activeEffects.add(effect);
        Initializer.returnWrite(null);
    }

    /**
     *
     * @param effect
     */
    public void removeEffect(Effect effect) {
        Initializer.functionWrite(
                new OutputObject(this),
                "removeEffect",
                OutputObject.generateParamsArray(effect)
        );

        activeEffects.remove(effect);
        Initializer.returnWrite(null);
    }

    /**
     *
     */
    public void pass() {
        Initializer.functionWrite(
                new OutputObject(this),
                "pass",
                null
        );
        actionsLeft = 0;
        Initializer.returnWrite(null);
    }

    /**
     *
     */
    public void myTurn() {
        Initializer.functionWrite(
                new OutputObject(this),
                "myTurn",
                null
        );

        //active effects onTurnImpact
        for (Effect e : activeEffects) {
            e.onTurnImpact(this);
        }

        //active effects endTurnImpact
        for (Effect e : activeEffects) {
            e.endTurnImpact(this);
        }

        //active effects decrement
        for (Effect e : activeEffects) {
            e.decrement(this);
        }

        //active agents decrement
        ArrayList<Agent> craftedAgents = inventory.getCraftedAgents();
        for (Agent a : craftedAgents) {
            a.decrement(this);
        }
        Initializer.returnWrite(null);
    }

    /**
     *
     * @param newTile
     */
    //TODO this should be private, but we need to use it for some tests
    public void moveTo(Tile newTile) {
        Initializer.functionWrite(
                new OutputObject(this),
                "moveTo",
                OutputObject.generateParamsArray(newTile)
        );

        activeTile.removeVirologist(this);
        newTile.addVirologist(this);
        Initializer.returnWrite(null);
    }

    /**
     *
     */
    public void pickUp() {
        Initializer.functionWrite(
                new OutputObject(this),
                "pickUp",
                null
        );
        activeTile.collectItem(inventory);
        controller.checkWinner(this);
        Initializer.returnWrite(null);
    }

    /**
     *
     * @param code
     */
    public void craft(GeneticCode code) {
        Initializer.functionWrite(
                new OutputObject(this),
                "craft",
                OutputObject.generateParamsArray(code)
        );
        code.craft(inventory);
        Initializer.returnWrite(null);
    }

    /**
     *
     * @param agent
     * @param v
     */
    public void useAgent(Agent agent, Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "useAgent",
                OutputObject.generateParamsArray(agent, v)
        );

        ArrayList<Agent> craftedAgents = inventory.getCraftedAgents();
        ArrayList<String> craftedAgentsStr = new ArrayList<>();
        for (Agent a : craftedAgents) {
            craftedAgentsStr.add(a.toString());
        }
        int idxAgent = Initializer.questionListWrite("Select the agent to use", craftedAgentsStr).getIndex();

        //TODO for Ádám, erre van külön függvény, az kell egyáltalán?
        ArrayList<Virologist> nearbyVirologists = activeTile.getPlayers();
        ArrayList<String> nearVirologistStr = new ArrayList<>();
        for (Virologist vir : nearbyVirologists) {
            nearVirologistStr.add(vir.getName());
        }
        int idxPlayer = Initializer.questionListWrite("Select the virologist to put agent on it", nearVirologistStr).getIndex();

        craftedAgents.get(idxAgent).use(this, nearbyVirologists.get(idxPlayer));

        Initializer.returnWrite(null);
    }

    /**
     *
     * @param v
     */
    public void steal(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "steal",
                OutputObject.generateParamsArray(v)
        );

        Inventory inv = v.getInventory();
        if (Initializer.questionYesOrNo("Does the virologist that is trying to steal have space for more equipments?")  &&
                Initializer.questionYesOrNo("Does the virologist that is being robbed have any equipments?")) {
            ArrayList<Equipment> equipments = inv.getEquipments();
            ArrayList<String> equipmentsString = new ArrayList<>();
            for (Equipment e : equipments) {
                equipmentsString.add(e.toString());
            }
            int result = Initializer.questionListWrite("Which equipment would you like to steal?", equipmentsString).getIndex();
            inventory.steal(inv, equipments.get(result));
        } else {
            inventory.steal(inv, null);
        }

        Initializer.returnWrite(null);
    }

    /**
     *
     * @return
     */
    private ArrayList<Tile> getNeighbours() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getNeighbours",
                null
        );
        Initializer.returnWrite(new OutputObject(activeTile.getNeighbours()));

        return activeTile.getNeighbours();
    }

    /**
     *
     * @return
     */
    private ArrayList<GeneticCode> getCraftables() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCraftables",
                null
        );

        ArrayList<GeneticCode> geneticCodes = inventory.getGeneticCodes();
        ArrayList<GeneticCode> craftables = new ArrayList<>();
        for (GeneticCode c : geneticCodes) {
            if (c.isCraftable(inventory)) {
                craftables.add(c);
            }
        }
        Initializer.returnWrite(new OutputObject(craftables));

        return craftables;
    }

    /**
     *
     * @return
     */
    private ArrayList<Agent> getCraftedAgents() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getCraftedAgents",
                null
        );

        ArrayList<Agent> craftedAgents = inventory.getCraftedAgents();

        Initializer.returnWrite(new OutputObject(craftedAgents));
        return craftedAgents;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public int getActionsLeft() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getActionsLeft",
                null
        );
        Initializer.returnWrite(new OutputObject(actionsLeft));

        return actionsLeft;
    }

    /**
     *
     * @return
     */
    public Tile getActiveTile() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getActiveTile",
                null
        );
        Initializer.returnWrite(new OutputObject(activeTile));

        return activeTile;
    }

    /**
     *
     * @param activeTile
     */
    public void setActiveTile(Tile activeTile) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setActiveTile",
                OutputObject.generateParamsArray(activeTile)
        );
        Initializer.returnWrite(null);

        this.activeTile = activeTile;
    }

    /**
     *
     * @return
     */
    public Inventory getInventory() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getInventory",
                null
        );
        Initializer.returnWrite(new OutputObject(inventory));

        return inventory;
    }

    /**
     *
     * @return
     */
    public ArrayList<Effect> getActiveEffects() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getActiveEffects",
                null
        );
        Initializer.returnWrite(new OutputObject(activeEffects));

        return activeEffects;
    }

    /**
     *
     * @return
     */
    public String getName() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getName",
                null
        );
        Initializer.returnWrite(new OutputObject(name));
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName (String name) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setName",
                OutputObject.generateParamsArray(name)
        );
        this.name = name;
        Initializer.returnWrite(null);
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
