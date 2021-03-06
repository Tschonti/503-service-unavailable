package main;

import static main.Constants.maxNumOfEquipments;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
import java.util.ArrayList;

/**
 * Every Virologist has an inventory and the other way around as well.
 * It stores the collected resources, equipments, agents and genetic codes.
 * It also has a maxResourceAmount and stores the virologist too.
 * The inventory manages all these things (adding, removing etc.)
 * and does the stealing mechanic.
 */
public class Inventory {

    /**
     * The max amount of resource this inventory can hold.
     */
    private int maxResourceAmount;

    /**
     * The virologist that has this inventory.
     */
    private final Virologist virologist;

    /**
     * The resources this inventory holds.
     */
    private final ArrayList<Resource> resources;

    /**
     * All the picked up equipments this inventory holds.
     */
    private final ArrayList<Equipment> pickedUpEquipments;

    /**
     * All the agents that have been crafted.
     */
    private final ArrayList<Agent> craftedAgents;

    /**
     * All the codes that the virologist has learnt.
     */
    private final ArrayList<GeneticCode> learntCodes;

    /**
     * All the usable equipments that the virologist has picked up.
     */
    private final ArrayList<UsableEquipment> usableEquipments;

    /**
     * Constructor
     * @param virologist The virologist this inventory belongs to.
     */
    public Inventory(Virologist virologist) {
        this.maxResourceAmount = Constants.baseResAmount;
        this.virologist = virologist;
        resources = new ArrayList<>();
        pickedUpEquipments = new ArrayList<>();
        craftedAgents = new ArrayList<>();
        learntCodes = new ArrayList<>();
        usableEquipments = new ArrayList<>();
        Resource.initializeResourceArray(resources);
    }

    /**
     * Getter for learntCodes.
     * @return learntCodes
     */
    public ArrayList<GeneticCode> getLearntCodes() {
        return learntCodes;
    }

    /**
     * Adds the genetic code to the learntCodes.
     * @param gc The genetic code to be added to this inventory.
     */
    public void addGeneticCode(GeneticCode gc) {
        learntCodes.add(gc);
    }

    /**
     * Adds the resource to the resources.
     * @param res The resource to be added to this inventory.
     * @return The added amount of resource.
     */
    public Resource addResource(Resource res) {
        Resource resToAdd = Resource.getResourceByType(resources, res.getType());
        if (resToAdd == null) {
            resToAdd = new Resource(0, res.getType());
        }
        int added = resToAdd.addAmount(maxResourceAmount, res.getAmount());
        if (added == 0 && res.getAmount() > 0) {
            throw new IllegalStateException("You don't have any space in your inventory!");
        }

        return new Resource(added, res.getType());
    }

    /**
     * Adds the agent to craftedAgents.
     * @param agent The agent to be added to this inventory.
     */
    public void addCraftedAgent(Agent agent) {
        craftedAgents.add(agent);
    }

    /**
     * Adds the equipment to pickedUpEquipments and its effect to the virologist.
     * @param eq The equipment to be added to this inventory.
     */
    public void addEquipment(Equipment eq) {
        if (pickedUpEquipments.size() < maxNumOfEquipments) {
            pickedUpEquipments.add(eq);
            virologist.addEffect(eq);
            eq.onTurnImpact(virologist);
        } else {
            throw new IllegalStateException(
                "You can't have more than " + maxNumOfEquipments + " equipments!"
            );
        }
    }

    /**
     * Removes the resource from resources.
     * @param res The resource to be removed from this inventory.
     */
    public void removeResource(Resource res) {
        Resource.getResourceByType(resources, res.getType()).removeAmount(res.getAmount());
    }

    /**
     * Removes the agent from craftedAgents.
     * @param agent The agent to be removed from this inventory.
     */
    public void removeCraftedAgent(Agent agent) {
        craftedAgents.remove(agent);
    }

    /**
     * Removes the equipment from pickedUpEquipments and its effect from the virologist.
     * @param eq The equipment to be removed from this inventory.
     */
    public void removeEquipment(Equipment eq) {
        if (Virologist.getController().getActivePlayer() == virologist) {
            eq.endTurnImpact(virologist);
        }
        virologist.removeEffect(eq);
        pickedUpEquipments.remove(eq);
    }

    /**
     * Getter for maxResourceAmount.
     * @return maxResourceAmount
     */
    public int getMaxResourceAmount() {
        return maxResourceAmount;
    }

    /**
     * Getter for resources.
     * @return resources
     */
    public ArrayList<Resource> getResources() {
        return resources;
    }

    /**
     * Setter for maxResourceAmount.
     * @param amount The new resource amount.
     */
    public void setMaxResourceAmount(int amount) {
        maxResourceAmount = amount;
    }

    /**
     * Getter for pickedUpEquipments.
     * @return pickedUpEquipments
     */
    public ArrayList<Equipment> getEquipments() {
        return pickedUpEquipments;
    }

    /**
     * Stealing from another player (or rather from their inventory).
     * It removes all the stolen equipment and resource from the other virologists inventory
     * and adds it to this inventory.
     * @param v2Inv The inventory of the virologist, which this inventory steals from.
     * @param eq The equipment this inventory tries to steal.
     */
    public void steal(Inventory v2Inv, Equipment eq) {
        if (eq != null) {
            v2Inv.removeEquipment(eq);
            addEquipment(eq);
            try {
                v2Inv.removeUsableEquipment((UsableEquipment) eq);
                addUsableEquipment((UsableEquipment) eq);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        ArrayList<Resource> inv2Resources = v2Inv.getResources();
        for (Resource res : inv2Resources) {
            Resource addedResource = addResource(res);
            v2Inv.removeResource(addedResource);
        }
    }

    /**
     * Getter for craftedAgents.
     * @return craftedAgents
     */
    public ArrayList<Agent> getCraftedAgents() {
        return craftedAgents;
    }

    /**
     * Adds a usable equipment to the usableEquipments.
     * @param usableEquipment The usable equipment we want to add.
     */
    public void addUsableEquipment(UsableEquipment usableEquipment) {
        if (pickedUpEquipments.size() < maxNumOfEquipments) {
            usableEquipments.add(usableEquipment);
        }
    }

    /**
     * Removes a usable equipment to the usableEquipments.
     * @param usableEquipment The usable equipment we want to remove.
     */
    public void removeUsableEquipment(UsableEquipment usableEquipment) {
        usableEquipments.remove(usableEquipment);
    }

    /**
     * toString method for the inventory.
     * @return A string from the inventory.
     */
    @Override
    public String toString() {
        return (
            "Inventory{" +
            "maxResourceAmount=" +
            maxResourceAmount +
            ", virologist=" +
            virologist +
            ", resources=" +
            resources +
            ", pickedUpEquipments=" +
            pickedUpEquipments +
            ", craftedAgents=" +
            craftedAgents +
            ", learntCodes=" +
            learntCodes +
            '}'
        );
    }

    /**
     * Getter for usableEquipments.
     * @return usableEquipments
     */
    public ArrayList<UsableEquipment> getUsableEquipments() {
        return usableEquipments;
    }
}
