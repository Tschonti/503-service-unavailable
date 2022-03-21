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

    public Inventory(Virologist virologist){
        this.virologist=virologist;
        resources=new ArrayList<>();
        pickedUpEquipments=new ArrayList<>();
        craftedAgents=new ArrayList<>();
        learntCodes=new ArrayList<>();
    }

    public void addGeneticCode(GeneticCode gc){
        Initializer.returnWrite(null);
    }

    public void addResource(Resource res){
        Initializer.returnWrite(null);
    }

    public void addCraftedAgent(Agent agent){
        Initializer.returnWrite(null);
    }

    public void addEquipment(Equipment eq){
        Initializer.returnWrite(null);
    }

    public void removeGeneticCode(GeneticCode gc){
        Initializer.returnWrite(null);
    }

    public void removeResource(Resource res){
        Initializer.returnWrite(null);
    }

    public void removeCraftedAgent(Agent agent){
        Initializer.returnWrite(null);
    }

    public boolean removeEquipment(Equipment eq){
        Initializer.returnWrite(new OutputObject(false));
        return false;
    }
}
