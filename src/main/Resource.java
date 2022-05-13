package main;

import observables.ObservableResource;

import java.util.ArrayList;

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
     * Observable for the resource.
     */
    private final ObservableResource view;
    /**
     * Constructor
     * @param a Amount of the Resource.
     * @param t Type of the Resource.
     */
    public Resource(int a, ResourceType t) {
        amount = a;
        type = t;
        view = new ObservableResource(this);
    }

    /**
     * Add a clone of this resource to a virologist's inventory.
     * @param inv Inventory to add the resource.
     */
    public void collect(Inventory inv) {
        inv.addResource(this);
    }

    /**
     * Makes a clone of this resource.
     * @return Clone of this resource.
     */
    public Collectable cloneCollectable() {
        return new Resource(amount, type);
    }

    /**
     * Getter for this resource's amount.
     * @return Resource's amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter for this resource's type.
     * @return Resource's type.
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Getter for the view of the resource
     * @return The view observing this resource
     */
    public ObservableResource getView() {
        return view;
    }

    /**
     * Add an amount of resource to this resource.
     * @param maxAmount Maximum amount of resource.
     * @param addedAmount Resource amount.
     * @return New amount of resource.
     */
    public int addAmount(int maxAmount, int addedAmount) {
        int overload = 0;
        amount += addedAmount;
        if (amount > maxAmount) {
            overload = amount - maxAmount;
            amount = maxAmount;
        }

        return addedAmount - overload;
    }

    /**
     * Subtract an amount of resource from this resource.
     * @param removedAmount Resource amount to subtract.
     */
    public void removeAmount(int removedAmount) {
        amount -= removedAmount;
        if (amount < 0) {
            amount = 0;
        }
    }

    /**
     * Selects a specific resource from a resource list.
     * @param resources List to select the specific resource from.
     * @param type Specific resource type.
     * @return Specific resource or null, if it doesn't exist in the array.
     */
    public static Resource getResourceByType(ArrayList<Resource> resources, ResourceType type) {
        for (Resource r : resources) {
            if (r.getType() == type) {
                return r;
            }
        }

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

    public String toString() {
        return type + ": " + amount;
    }
}
