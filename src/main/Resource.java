package main;

import java.util.ArrayList;
import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * This class represents the Resources in the game. It can be collected by virologists from warehouses.
 */
public class Resource implements Collectable {

    /**
     * Resource's amount.
     */
    private int amount;

    /**
     * Resource's type.
     */
    private final ResourceType type;

    /**
     * Constructor
     * @param a Amount of the Resource.
     * @param t Type of the Resource.
     */
    public Resource(int a, ResourceType t) {
        Initializer.functionWrite(
            new OutputObject(this),
            "constructor",
            OutputObject.generateParamsArray(a, t)
        );

        amount = a;
        type = t;

        Initializer.returnWrite(null);
    }

    /**
     * Add a clone of this resource to a virologist's inventory.
     * @param inv Inventory to add the resource.
     */
    public void collect(Inventory inv) {
        Initializer.functionWrite(
            new OutputObject(this),
            "collect",
            OutputObject.generateParamsArray(inv)
        );

        //A clone will be collected
        Resource newR = new Resource(amount, type);
        inv.addResource(newR);

        Initializer.returnWrite(null);
    }

    /**
     * Makes a clone of this resource.
     * @return Clone of this resource.
     */
    public Collectable cloneCollectable() {
        Initializer.functionWrite(new OutputObject(this), "cloneCollectable", null);

        Collectable clone = new Resource(amount, type);
        Initializer.returnWrite(new OutputObject(clone));
        return clone;
    }

    /**
     * Getter for this resource's amount.
     * @return Resource's amount.
     */
    public int getAmount() {
        Initializer.functionWrite(new OutputObject(this), "getAmount", null);

        Initializer.returnWrite(new OutputObject(amount));

        return amount;
    }

    /**
     * Setter for this resource's amount.
     * @param amount Resource's amount.
     */
    public void setAmount(int amount) {
        Initializer.functionWrite(
            new OutputObject(this),
            "setAmount",
            OutputObject.generateParamsArray(amount)
        );
        Initializer.returnWrite(null);

        this.amount = amount;
    }

    /**
     * Getter for this resource's type.
     * @return Resource's type.
     */
    public ResourceType getType() {
        Initializer.functionWrite(new OutputObject(this), "getType", null);
        Initializer.returnWrite(new OutputObject(type));

        return type;
    }

    /**
     * Add an amount of resource to this resource.
     * @param maxAmount Maximum amount of resource.
     * @param addedAmount Resource amount.
     * @return New amount of resource.
     */
    public int addAmount(int maxAmount, int addedAmount) {
        Initializer.functionWrite(
            new OutputObject(this),
            "addAmount",
            OutputObject.generateParamsArray(maxAmount, addedAmount)
        );

        int overload = 0;
        amount += addedAmount;
        if (amount > maxAmount) {
            overload = amount - maxAmount;
            amount = maxAmount;
        }

        Initializer.returnWrite(new OutputObject(amount));

        return addedAmount - overload;
    }

    /**
     * Subtract an amount of resource from this resource.
     * @param removedAmount Resource amount to subtract.
     */
    public void removeAmount(int removedAmount) {
        Initializer.functionWrite(
            new OutputObject(this),
            "addAmount",
            OutputObject.generateParamsArray(removedAmount)
        );

        amount -= removedAmount;
        if (amount < 0) {
            amount = 0;
        }

        Initializer.returnWrite(null);
    }

    /**
     * Selects a specific resource from a resource list.
     * @param resources List to select the specific resource from.
     * @param type Specific resource type.
     * @return Specific resource or null, if it doesn't exist in the array.
     */
    public static Resource getResourceByType(ArrayList<Resource> resources, ResourceType type) {
        Initializer.functionWrite(
            new OutputObject("Resource", true),
            "static getResourcesByType",
            OutputObject.generateParamsArray(resources, type)
        );

        for (Resource r : resources) {
            if (r.getType() == type) {
                Initializer.returnWrite(new OutputObject(r));

                return r;
            }
        }

        Initializer.returnWrite(null);
        return null;
    }

    /**
     * Adds a new resource object of every type with 0 as the amount to the array.
     * Should be called on an empty list.
     * @param resources The resource array to be initialized.
     */
    public static void initializeResourceArray(ArrayList<Resource> resources) {
        for (ResourceType t : ResourceType.values()) {
            resources.add(new Resource(0, t));
        }
    }
}
