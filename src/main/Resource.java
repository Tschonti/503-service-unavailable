package main;

import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.ArrayList;

public class Resource implements Collectable {
    private int amount;
    private ResourceType type;

    /**
     *
     * @param a
     * @param t
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
     *
     * @param inv
     */
    public void collect(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collect",
                OutputObject.generateParamsArray(inv)
        );

        inv.addResource(this);

        Initializer.returnWrite(null);
    }

    public Collectable clone() {
        Initializer.functionWrite(
                new OutputObject(this),
                "clone",
                null
        );

        Collectable clone = new Resource(amount, type);
        Initializer.returnWrite(new OutputObject(clone));
        return clone;
    }

    /**
     *
     * @return
     */
    public int getAmount() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getAmount",
                null
        );

        Initializer.returnWrite(new OutputObject(amount));

        return amount;
    }

    /**
     *
     * @param amount
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
     *
     * @return
     */
    public ResourceType getType() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getType",
                null
        );
        Initializer.returnWrite(new OutputObject(type));

        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(ResourceType type) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setType",
                OutputObject.generateParamsArray(type)
        );
        this.type = type;

        Initializer.returnWrite(null);
    }

    public int addAmount(int maxAmount, int addedAmount) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addAmount",
                OutputObject.generateParamsArray(maxAmount, addedAmount)
        );
        Initializer.returnWrite(new OutputObject(0));
        return 0;
    }

    public void removeAmount(int removedAmount) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addAmount",
                OutputObject.generateParamsArray(removedAmount)
        );
        Initializer.returnWrite(null);
    }

    /**
     *
     * @param resources
     * @param type
     * @return specific recourse or if it's not exist in the array --> null
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

    // TODO full nem szerepelt szekvenciákon úgyhogy ne írjuk ki szerintem de jó ha van
    public static void initializeResourceArray(ArrayList<Resource> resources) {
        for (ResourceType t : ResourceType.values()) {
            resources.add(new Resource(0, t));
        }
    }
}
