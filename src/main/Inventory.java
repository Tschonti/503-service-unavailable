package main;

import agents.Agent;
import equipments.Equipment;
import skeleton.Initializer;
import skeleton.OutputObject;
import java.util.ArrayList;

/**
 * Inventory
 * Every Virologist has an inventory and the other way around as well.
 * It stores the collected resources, equipments, agents and genetic codes.
 * It also has a maxResourceAmount and stores the virologist too.
 * The inventory manages all these things (adding, removing etc.)
 * and does the stealing mechanic.
 */
public class Inventory {
    private int maxResourceAmount;
    private Virologist virologist;
    private ArrayList<Resource> resources;
    private ArrayList<Equipment> pickedUpEquipments;
    private ArrayList<Agent> craftedAgents;
    private ArrayList<GeneticCode> learntCodes;

    /**
     * Constructor
     * @param virologist
     */
    public Inventory(Virologist virologist) {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                OutputObject.generateParamsArray(virologist)
        );

        this.virologist = virologist;
        resources = new ArrayList<>();
        pickedUpEquipments = new ArrayList<>();
        craftedAgents = new ArrayList<>();
        learntCodes = new ArrayList<>();

        Initializer.returnWrite(null);
    }

    /**
     * Adds the genetic code to the learntCodes.
     * @param gc
     */
    public void addGeneticCode(GeneticCode gc){
        Initializer.functionWrite(
                new OutputObject(this),
                "addGeneticCode",
                OutputObject.generateParamsArray(gc)
        );

        learntCodes.add(gc);

        Initializer.returnWrite(null);
    }

    /**
     * Adds the resource to the resources.
     * @param res
     * @return The added amount of resource.
     */
    public Resource addResource(Resource res){
        Initializer.functionWrite(
                new OutputObject(this),
                "addResource",
                OutputObject.generateParamsArray(res)
        );
        Resource resToAdd=Resource.getResourceByType(resources, res.getType());
        if(resToAdd==null){
            resToAdd=new Resource(0,res.getType());
        }
        int added = resToAdd.addAmount(maxResourceAmount, res.getAmount());
        Resource ret = new Resource(added, res.getType());

        Initializer.returnWrite(new OutputObject(ret));
        return ret;
    }

    /**
     * Adds the agent to craftedAgents.
     * @param agent
     */
    public void addCraftedAgent(Agent agent){
        Initializer.functionWrite(
                new OutputObject(this),
                "addCraftedAgent",
                OutputObject.generateParamsArray(agent)
        );

        craftedAgents.add(agent);

        Initializer.returnWrite(null);
    }

    /**
     * Adds the equipment to pickedUpEquipments and its effect to the virologist.
     * @param eq
     */
    public void addEquipment(Equipment eq){
        Initializer.functionWrite(
                new OutputObject(this),
                "addEquipment",
                OutputObject.generateParamsArray(eq)
        );

        pickedUpEquipments.add(eq);
        virologist.addEffect(eq);

        Initializer.returnWrite(null);
    }

    /**
     * Removes the genetic code from learntCodes.
     * @param gc
     */
    public void removeGeneticCode(GeneticCode gc){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeGeneticCode",
                OutputObject.generateParamsArray(gc)
        );

        learntCodes.remove(gc);

        Initializer.returnWrite(null);
    }

    /**
     * Removes the resource from resources.
     * @param res
     */
    public void removeResource(Resource res){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeResource",
                OutputObject.generateParamsArray(res)
        );

        Resource.getResourceByType(resources, res.getType()).removeAmount(res.getAmount());

        Initializer.returnWrite(null);
    }

    /**
     * Removes the agent from craftedAgents
     * @param agent
     */
    public void removeCraftedAgent(Agent agent){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeCraftedAgent",
                OutputObject.generateParamsArray(agent)
        );

        craftedAgents.remove(agent);

        Initializer.returnWrite(null);
    }

    /**
     * Removes the equipment from pickedUpEquipments and its effect from the virologist.
     * @param eq
     * @return
     */
    public boolean removeEquipment(Equipment eq){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeEquipment",
                OutputObject.generateParamsArray(eq)
        );

        virologist.removeEffect(eq);
        pickedUpEquipments.remove(eq);

        Initializer.returnWrite(new OutputObject(false));

        return false;
    }

    /**
     * Getter for maxResourceAmount.
     * @return maxResourceAmount
     */
    public int getMaxResourceAmount(){
        Initializer.functionWrite(
                new OutputObject(this),
                "getMaxResourceAmount",
                null
        );
        Initializer.returnWrite(new OutputObject(maxResourceAmount));
        return maxResourceAmount;
    }

    /**
     * Getter for resources.
     * @return resources
     */
    public ArrayList<Resource> getResources() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getResources",
                null
        );
        Initializer.returnWrite(new OutputObject(resources));
        return resources;
    }

    /**
     * Setter for maxResourceAmount.
     * @param amount
     */
    public void setMaxResourceAmount(int amount){
        Initializer.functionWrite(
                new OutputObject(this),
                "setMaxResourceAmount",
                OutputObject.generateParamsArray(amount)
        );

        maxResourceAmount = amount;

        Initializer.returnWrite(null);
    }

    /**
     * Getter for pickedUpEquipments.
     * @return pickedUpEquipments
     */
    public ArrayList<Equipment> getEquipments() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getEquipments",
                null
        );
        Initializer.returnWrite(new OutputObject(pickedUpEquipments));
        return pickedUpEquipments;
    }

    /**
     * Stealing from another player (or rather from their inventory).
     * It removes all the stolen equipment and resource from the other virologists inventory
     * and adds it to this inventory.
     * @param v2Inv
     * @param eq
     */
    public void steal(Inventory v2Inv, Equipment eq) {
        Initializer.functionWrite(
                new OutputObject(this),
                "steal",
                OutputObject.generateParamsArray(v2Inv, eq)
        );

        v2Inv.removeEquipment(eq);
        addEquipment(eq);
        eq.onTurnImpact(virologist);
        ArrayList<Resource> inv2Resources =  v2Inv.getResources();
        for (Resource res : inv2Resources) {
            Resource addedResource = addResource(res);
            v2Inv.removeResource(addedResource);
        }

        Initializer.returnWrite(null);
    }

    public ArrayList<Agent> getCraftedAgents() {
        return craftedAgents;
    }

    public ArrayList<GeneticCode> getGeneticCodes() {
        return new ArrayList<>();
    }
}
