package main;

import agents.Agent;
import equipments.Equipment;
import java.util.ArrayList;

import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

/**
 * This class represents the player. Virologist class responsible for their movements, inventories and actions.
 */
public class Virologist {

    /**
     * The number of actions remaining in this round.
     */
    private int actionsLeft;

    /**
     * Static game controller.
     */
    private static Controller controller;

    /**
     * The tile that this virologist is on.
     */
    private Tile activeTile;

    /**
     * Virologist's inventory.
     */
    private final Inventory inventory;

    /**
     * The active effects are on this virologist.
     */
    private final ArrayList<Effect> activeEffects;

    /**
     * Virologist's unique name.
     */
    private String name;

    /**
     * Constructor
     * @param name Virologist's unique name.
     */
    public Virologist(String name) {
        Initializer.functionWrite(new OutputObject(this), "constructor", null);
        actionsLeft = Constants.numberOfActions;
        activeEffects = new ArrayList<>();
        inventory = new Inventory(this);
        this.name = name;
        Initializer.returnWrite(null);
    }

    /**
     * Adds an effect to the virologist's active effects.
     * @param effect Effect will be added to the active effects.
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
     * Remove an effect from the virologist's active effects.
     * @param effect Effect will be removed from the active effects.
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
     * An actions with no impact.
     */
    public void pass() {
        Initializer.functionWrite(new OutputObject(this), "pass", null);
        actionsLeft--;
        Initializer.returnWrite(null);
    }

    /**
     * All active effects do their impacts and decrement the lifetimes.
     */
    public void myTurn() {
        Initializer.functionWrite(new OutputObject(this), "myTurn", null);

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
     * Moves the virologist to another tile.
     * @param newTile The new tile where the virologist moves to.
     */
    public void moveTo(Tile newTile) {
        Initializer.functionWrite(
            new OutputObject(this),
            "moveTo",
            OutputObject.generateParamsArray(newTile)
        );

        activeTile.removeVirologist(this);
        newTile.addVirologist(this);
        actionsLeft--;
        Initializer.returnWrite(null);
    }

    /**
     * Virologist picks up the collectible item from a tile.
     */
    public void pickUp() {
        Initializer.functionWrite(new OutputObject(this), "pickUp", null);
        activeTile.collectItem(inventory);
        controller.checkWinner(this);
        Initializer.returnWrite(null);
    }

    /**
     * Craft an agent from a genetic code.
     * @param code Agent will be crafted from this genetic code.
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
     * Use agent to a target virologist. That can be another virologist or themselves.
     * @param agent Agent that will be used.
     * @param v Virologist to use the agent on.
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
        int idxAgent = Initializer
            .questionListWrite("Select the agent to use", craftedAgentsStr)
            .getIndex();

        ArrayList<Virologist> nearbyVirologists = activeTile.getPlayers();
        ArrayList<String> nearVirologistStr = new ArrayList<>();
        for (Virologist vir : nearbyVirologists) {
            nearVirologistStr.add(vir.getName());
        }
        int idxPlayer = Initializer
            .questionListWrite("Select the virologist to put agent on it", nearVirologistStr)
            .getIndex();

        craftedAgents.get(idxAgent).use(this, nearbyVirologists.get(idxPlayer));

        Initializer.returnWrite(null);
    }

    /**
     * Steal from another Virologist.
     * @param v Virologist to steal from.
     */
    public void steal(Virologist v) {
        Initializer.functionWrite(
            new OutputObject(this),
            "steal",
            OutputObject.generateParamsArray(v)
        );

        Inventory inv = v.getInventory();
        if (
            Initializer.questionYesOrNo(
                "Does the virologist that is trying to steal have space for more equipments?"
            ) &&
            Initializer.questionYesOrNo(
                "Does the virologist that is being robbed have any equipments?"
            )
        ) {
            ArrayList<Equipment> equipments = inv.getEquipments();
            ArrayList<String> equipmentsString = new ArrayList<>();
            for (Equipment e : equipments) {
                equipmentsString.add(OutputObject.objectToName(e));
            }
            int result = Initializer
                .questionListWrite("Which equipment would you like to steal?", equipmentsString)
                .getIndex();
            inventory.steal(inv, equipments.get(result));
        } else {
            inventory.steal(inv, null);
        }

        Initializer.returnWrite(null);
    }

    /**
     * Returns the tiles where the virologist can move to.
     * @return Tiles where virologist can move to.
     */
    private ArrayList<Tile> getNeighbours() {
        Initializer.functionWrite(new OutputObject(this), "getNeighbours", null);
        Initializer.returnWrite(new OutputObject(activeTile.getNeighbours()));

        return activeTile.getNeighbours();
    }

    /**
     * Returns the collected genetic codes that the  virologist can craft.
     * @return Genetic codes that virologist can craft.
     */
    private ArrayList<GeneticCode> getCraftables() {
        Initializer.functionWrite(new OutputObject(this), "getCraftables", null);

        ArrayList<GeneticCode> geneticCodes = inventory.getLearntCodes();
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
     * Returns the crafted agents from the inventory.
     * @return Crafted agents.
     */
    private ArrayList<Agent> getCraftedAgents() {
        Initializer.functionWrite(new OutputObject(this), "getCraftedAgents", null);

        ArrayList<Agent> craftedAgents = inventory.getCraftedAgents();

        Initializer.returnWrite(new OutputObject(craftedAgents));
        return craftedAgents;
    }

    /**
     * Returns the virologists that this virologist can touch.
     * @return Virologists that this virologist can touch.
     */
    private ArrayList<Virologist> getNearbyVirologists() {
        Initializer.functionWrite(new OutputObject(this), "getNearbyVirologist", null);

        //Virologist remove itself from the list. A new list is needed because of the reference
        ArrayList<Virologist> result = new ArrayList<>(activeTile.getPlayers());
        result.remove(this);
        Initializer.returnWrite(new OutputObject(result));

        return result;
    }

    /**
     * Returns the virologists that this virologist can steal from.
     * @return Virologists that this virologist can steal from.
     */
    private ArrayList<Virologist> getNearbyVirologistsToStealFrom() {
        Initializer.functionWrite(new OutputObject(this), "getNearbyVirologistToStealFrom", null);

        //Virologist remove itself from the list. A new list is needed because of the reference
        ArrayList<Virologist> result = new ArrayList<>(activeTile.getPlayersToStealFrom());
        result.remove(this);
        Initializer.returnWrite(new OutputObject(result));

        return result;
    }

    /**
     * Getter for the number of actions remaining in this round.
     * @return The number of actions remaining in this round.
     */
    public int getActionsLeft() {
        Initializer.functionWrite(new OutputObject(this), "getActionsLeft", null);
        Initializer.returnWrite(new OutputObject(actionsLeft));

        return actionsLeft;
    }

    /**
     * Getter for the tile that this virologist is on.
     * @return Tile that this virologist is on.
     */
    public Tile getActiveTile() {
        Initializer.functionWrite(new OutputObject(this), "getActiveTile", null);
        Initializer.returnWrite(new OutputObject(activeTile));

        return activeTile;
    }

    /**
     * Setter for the tile that this virologist is on.
     * @param activeTile Tile that this virologist is on.
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
     * Getter for this virologist's inventory.
     * @return Virologist's inventory.
     */
    public Inventory getInventory() {
        Initializer.functionWrite(new OutputObject(this), "getInventory", null);
        Initializer.returnWrite(new OutputObject(inventory));

        return inventory;
    }

    /**
     * Returns with the active effects are on this virologist.
     * @return Active effects are on this virologist.
     */
    public ArrayList<Effect> getActiveEffects() {
        Initializer.functionWrite(new OutputObject(this), "getActiveEffects", null);
        Initializer.returnWrite(new OutputObject(activeEffects));

        return activeEffects;
    }

    /**
     * Getter for virologist's name.
     * @return Virologist's name.
     */
    public String getName() {
        Initializer.functionWrite(new OutputObject(this), "getName", null);
        Initializer.returnWrite(new OutputObject(name));
        return name;
    }

    /**
     * Setter for virologist's name.
     * @param name Virologist's name.
     */
    public void setName(String name) {
        Initializer.functionWrite(
            new OutputObject(this),
            "setName",
            OutputObject.generateParamsArray(name)
        );
        this.name = name;
        Initializer.returnWrite(null);
    }

    /**
     * Setter for the static controller.
     * @param controller The main controller of the game.
     */
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
