package main;

import agents.Agent;
import com.sun.org.apache.xpath.internal.operations.Bool;
import equipments.Equipment;
import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.ArrayList;

public class Inventory {
    private int maxResourceAmount;
    private Virologist virologist;
    private ArrayList<Resource> resources;
    private ArrayList<Equipment> pickedUpEquipments;
    private ArrayList<Agent> craftedAgents;
    private ArrayList<GeneticCode> learntCodes;

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

    public void addGeneticCode(GeneticCode gc){
        Initializer.functionWrite(
                new OutputObject(this),
                "addGeneticCode",
                OutputObject.generateParamsArray(gc)
        );

        learntCodes.add(gc);

        Initializer.returnWrite(null);
    }

    public Resource addResource(Resource res){
        Initializer.functionWrite(
                new OutputObject(this),
                "addResource",
                OutputObject.generateParamsArray(res)
        );

        // TODO ennek majd nem így kell lennie, ellenőrizni kell a maxot
        resources.add(res);

        Initializer.returnWrite(new OutputObject(res));
        return null;
    }

    public void addCraftedAgent(Agent agent){
        Initializer.functionWrite(
                new OutputObject(this),
                "addCraftedAgent",
                OutputObject.generateParamsArray(agent)
        );

        craftedAgents.add(agent);

        Initializer.returnWrite(null);
    }

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

    public void removeGeneticCode(GeneticCode gc){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeGeneticCode",
                OutputObject.generateParamsArray(gc)
        );

        learntCodes.remove(gc);

        Initializer.returnWrite(null);
    }

    public void removeResource(Resource res){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeResource",
                OutputObject.generateParamsArray(res)
        );
        // TODO szintúgy
        resources.remove(res);

        Initializer.returnWrite(null);
    }

    public void removeCraftedAgent(Agent agent){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeCraftedAgent",
                OutputObject.generateParamsArray(agent)
        );

        craftedAgents.remove(agent);

        Initializer.returnWrite(null);
    }

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

    public int getMaxResourceAmount(){
        Initializer.functionWrite(
                new OutputObject(this),
                "getMaxResourceAmount",
                null
        );
        Initializer.returnWrite(new OutputObject(maxResourceAmount));
        return maxResourceAmount;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setMaxResourceAmount(int amount){
        Initializer.functionWrite(
                new OutputObject(this),
                "setMaxResourceAmount",
                OutputObject.generateParamsArray(amount)
        );

        maxResourceAmount = amount;

        Initializer.returnWrite(null);
    }

    public ArrayList<Equipment> getEquipments() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getEquipments",
                null
        );
        Initializer.returnWrite(new OutputObject(pickedUpEquipments));
        return pickedUpEquipments;
    }

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
}
