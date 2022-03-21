package main;

import java.util.ArrayList;

public class Resource implements Collectable {
    private int amount;
    private ResourceType type;

    public Resource(int a, ResourceType t) {
        amount = a;
        type = t;
    }

    public void collect(Inventory inv) {}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public static Resource getResourceByType(ArrayList<Resource> resources, ResourceType type) {
        return new Resource(0, ResourceType.Nucleotid);
    }
}
