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
        Initializer.returnWrite(null);

        this.virologist=virologist;
        resources=new ArrayList<>();
        pickedUpEquipments=new ArrayList<>();
        craftedAgents=new ArrayList<>();
        learntCodes=new ArrayList<>();
    }

    public void addGeneticCode(GeneticCode gc){
        Initializer.functionWrite(
                new OutputObject(this),
                "addGeneticCode",
                OutputObject.generateParamsArray(gc)
        );
        Initializer.returnWrite(null);
    }

    public void addResource(Resource res){
        Initializer.functionWrite(
                new OutputObject(this),
                "addResource",
                OutputObject.generateParamsArray(res)
        );
        Initializer.returnWrite(null);
    }

    public void addCraftedAgent(Agent agent){
        Initializer.functionWrite(
                new OutputObject(this),
                "addCraftedAgent",
                OutputObject.generateParamsArray(agent)
        );
        Initializer.returnWrite(null);
    }

    public void addEquipment(Equipment eq){
        Initializer.functionWrite(
                new OutputObject(this),
                "addEquipment",
                OutputObject.generateParamsArray(eq)
        );
        Initializer.returnWrite(null);
    }

    public void removeGeneticCode(GeneticCode gc){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeGeneticCode",
                OutputObject.generateParamsArray(gc)
        );
        Initializer.returnWrite(null);
    }

    public void removeResource(Resource res){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeResource",
                OutputObject.generateParamsArray(res)
        );
        Initializer.returnWrite(null);
    }

    public void removeCraftedAgent(Agent agent){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeCraftedAgent",
                OutputObject.generateParamsArray(agent)
        );
        Initializer.returnWrite(null);
    }

    public boolean removeEquipment(Equipment eq){
        Initializer.functionWrite(
                new OutputObject(this),
                "removeEquipment",
                OutputObject.generateParamsArray(eq)
        );
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
    public void setMaxResourceAmount(int amount){
        Initializer.functionWrite(
                new OutputObject(this),
                "setMaxResourceAmount",
                OutputObject.generateParamsArray(amount)
        );
        maxResourceAmount=amount;
        Initializer.returnWrite(null);
    }



    public ArrayList<Equipment> getEquipments() {
        return pickedUpEquipments;
    }

    public void steal(Inventory v, Equipment e) {
        Initializer.functionWrite(
                new OutputObject(this),
                "steal",
                OutputObject.generateParamsArray(v, e)
        );
        Initializer.returnWrite(null);
    }

    public ArrayList<Agent> getCraftedAgents() {
        return craftedAgents;
    }

    public ArrayList<GeneticCode> getGeneticCodes() {
        return new ArrayList<>();
    }
}
