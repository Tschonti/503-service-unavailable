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
        actionsLeft = Constants.numberOfActions;
        activeEffects = new ArrayList<>();
        inventory = new Inventory(this);
        this.name = name;
    }

    /**
     * Adds an effect to the virologist's active effects.
     * @param effect Effect will be added to the active effects.
     */
    public void addEffect(Effect effect) {
        activeEffects.add(effect);
    }

    /**
     * Remove an effect from the virologist's active effects.
     * @param effect Effect will be removed from the active effects.
     */
    public void removeEffect(Effect effect) {
        activeEffects.remove(effect);
    }

    /**
     * An actions with no impact.
     */
    public void pass() {
        actionsLeft--;
    }

    /**
     * All active effects do their impacts and decrement the lifetimes.
     */
    public void myTurn() {
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
    }

    /**
     * Moves the virologist to another tile.
     * @param newTile The new tile where the virologist moves to.
     */
    public void moveTo(Tile newTile) {
        activeTile.removeVirologist(this);
        newTile.addVirologist(this);
        actionsLeft--;
    }

    /**
     * Virologist picks up the collectible item from a tile.
     */
    public void pickUp() {
        activeTile.collectItem(inventory);
    }

    /**
     * Craft an agent from a genetic code.
     * @param code Agent will be crafted from this genetic code.
     */
    public void craft(GeneticCode code) {
        code.craft(inventory);
    }

    /**
     * Use agent to a target virologist. That can be another virologist or themselves.
     * @param agent Agent that will be used.
     * @param v Virologist to use the agent on.
     */
    public void useAgent(Agent agent, Virologist v) {
        ArrayList<Agent> craftedAgents = inventory.getCraftedAgents();
        ArrayList<String> craftedAgentsStr = new ArrayList<>();
        for (Agent a : craftedAgents) {
            craftedAgentsStr.add(a.toString());
        }
        int idxAgent = 0; //TODO, ez eddig kérdés volt

        ArrayList<Virologist> nearbyVirologists = activeTile.getPlayers();
        ArrayList<String> nearVirologistStr = new ArrayList<>();
        for (Virologist vir : nearbyVirologists) {
            nearVirologistStr.add(vir.getName());
        }
        int idxPlayer = 0; //TODO, ez eddig kérdés volt

        craftedAgents.get(idxAgent).use(this, nearbyVirologists.get(idxPlayer));
    }

    /**
     * Steal from another Virologist.
     * @param v Virologist to steal from.
     */
    public void steal(Virologist v) {
        Inventory inv = v.getInventory();
        if (true) { //TODO, kérdések voltak
            ArrayList<Equipment> equipments = inv.getEquipments();
            ArrayList<String> equipmentsString = new ArrayList<>();
            for (Equipment e : equipments) {
                equipmentsString.add(OutputObject.objectToName(e));
            }
            int result = 0; //TODO, ez eddig kérdés volt
            inventory.steal(inv, equipments.get(result));
        } else {
            inventory.steal(inv, null);
        }
    }

    /**
     * Returns the tiles where the virologist can move to.
     * @return Tiles where virologist can move to.
     */
    public ArrayList<Tile> getNeighbours() {
        return activeTile.getNeighbours();
    }

    /**
     * Returns the collected genetic codes that the  virologist can craft.
     * @return Genetic codes that virologist can craft.
     */
    private ArrayList<GeneticCode> getCraftables() {
        ArrayList<GeneticCode> geneticCodes = inventory.getLearntCodes();
        ArrayList<GeneticCode> craftables = new ArrayList<>();
        for (GeneticCode c : geneticCodes) {
            if (c.isCraftable(inventory)) {
                craftables.add(c);
            }
        }

        return craftables;
    }

    /**
     * Returns the crafted agents from the inventory.
     * @return Crafted agents.
     */
    public ArrayList<Agent> getCraftedAgents() {
        return inventory.getCraftedAgents();
    }

    /**
     * Returns the virologists that this virologist can touch.
     * @return Virologists that this virologist can touch.
     */
    public ArrayList<Virologist> getNearbyVirologists() {
        //Virologist remove itself from the list. A new list is needed because of the reference
        ArrayList<Virologist> result = new ArrayList<>(activeTile.getPlayers());
        result.remove(this);

        return result;
    }

    /**
     * Returns the virologists that this virologist can steal from.
     * @return Virologists that this virologist can steal from.
     */
    public ArrayList<Virologist> getNearbyVirologistsToStealFrom() {
        //Virologist remove itself from the list. A new list is needed because of the reference
        ArrayList<Virologist> result = new ArrayList<>(activeTile.getPlayersToStealFrom());
        result.remove(this);

        return result;
    }

    /**
     * Getter for the number of actions remaining in this round.
     * @return The number of actions remaining in this round.
     */
    public int getActionsLeft() {
        return actionsLeft;
    }

    /**
     * Getter for the tile that this virologist is on.
     * @return Tile that this virologist is on.
     */
    public Tile getActiveTile() {
        return activeTile;
    }

    /**
     * Setter for the tile that this virologist is on.
     * @param activeTile Tile that this virologist is on.
     */
    public void setActiveTile(Tile activeTile) {
        this.activeTile = activeTile;
    }

    /**
     * Getter for this virologist's inventory.
     * @return Virologist's inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns with the active effects are on this virologist.
     * @return Active effects are on this virologist.
     */
    public ArrayList<Effect> getActiveEffects() {
        return activeEffects;
    }

    /**
     * Getter for virologist's name.
     * @return Virologist's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for virologist's name.
     * @param name Virologist's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the static controller.
     * @param controller The main controller of the game.
     */
    public static void setController(Controller controller) {
        Virologist.controller = controller;
    }


    public void drop(Equipment eq) {
        //TODO
    }

    public void startTurn() {
        //TODO
    }

    public void endTurn() {
        //TODO
    }
}
