package main;

import agents.Agent;
import equipments.Equipment;
import tiles.Tile;

import java.util.ArrayList;

/**
 * This class represents the player. Virologist class responsible for their movements, inventories and actions.
 */
public class Virologist {

    /**
     * The number of actions remaining in current round.
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
     * List of active effects are on this virologist.
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
     * Removes an effect from the virologist's active effects.
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
     * This function is called before the virologist start it's round. It calls the active effects' impacts.
     */
    public void startTurn() {
        actionsLeft = 2;
        for (Effect e : activeEffects) {
            e.onTurnImpact(this);
        }
    }

    /**
     * This function is called after the virologist finished with its round. It calls the decrement functions.
     */
    public void endTurn() {
        //Call the crafted agents' decrement function
        ArrayList<Agent> craftedAgents = inventory.getCraftedAgents();
        for (Agent a : craftedAgents) {
            a.decrement(this);
        }

        //Active effects impact after the round and decrement their lifetime
        for (Effect e : activeEffects) {
            e.endTurnImpact(this);
            e.decrement(this);
        }
    }

    /**
     * Moves the virologist to another tile.
     * @param newTile The new tile where the virologist moves to.
     */
    public void moveTo(Tile newTile) {
        activeTile.removeVirologist(this);
        newTile.addVirologist(this);
        activeTile = newTile;
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
        agent.use(this, v);
    }

    /**
     * Virologist drop its equipment.
     * @param eq Equipment that will be removed.
     */
    public void drop(Equipment eq) {
        inventory.removeEquipment(eq);

        //If a bag was dropped we have to recalculate the resource amounts.
        ArrayList<Resource> resources = inventory.getResources();
        int maxAmount = inventory.getMaxResourceAmount();
        for (Resource r : resources) {
            if (r.getAmount() > maxAmount) {
                r.removeAmount(r.getAmount() - maxAmount);
            }
        }

    }

    /**
     * Steal from another Virologist.
     * @param v Virologist to steal from.
     */
    public void steal(Virologist v, Equipment eq) {
        Inventory inv = v.getInventory();
        inventory.steal(inv, eq);
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
        /*//Virologist remove itself from the list. A new list is needed because of the references, and we don't want to remove this virologist from the tile's players
        ArrayList<Virologist> result = new ArrayList<>(activeTile.getPlayers());
        //result.remove(this);
        return result;*/
        return activeTile.getPlayers();
        //TODO ez egy teljesen felesleges függvény
    }

    /**
     * Returns the virologists that this virologist can steal from.
     * @return Virologists that this virologist can steal from.
     */
    public ArrayList<Virologist> getNearbyVirologistsToStealFrom() {
        //Virologist remove itself from the list. A new list is needed because of the references, and we don't want to remove this virologist from the tile's players
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
     * Returns with a string that describes this virologist.
     * @return Virologist's unique name
     */
    public String toString() {
        return name;
    }

    /**
     * Setter for the static controller.
     * @param controller The main controller of the game.
     */
    public static void setController(Controller controller) {
        Virologist.controller = controller;
    }

    /**
     * Getter for the static controller
     * @return The main controller of the game
     */
    public static Controller getController() {
        return controller;
    }
}
