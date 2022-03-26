package main;

import skeleton.Initializer;
import skeleton.OutputObject;

import java.util.ArrayList;

public class Resource implements Collectable {
    private int amount;
    private ResourceType type;

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

    public int getAmount() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getAmount",
                null
        );
        Initializer.returnWrite(new OutputObject(amount));

        return amount;
    }

    public void setAmount(int amount) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setAmount",
                OutputObject.generateParamsArray(amount)
        );
        Initializer.returnWrite(null);

        this.amount = amount;
    }

    public ResourceType getType() {
        Initializer.functionWrite(
                new OutputObject(this),
                "getType",
                null
        );
        Initializer.returnWrite(new OutputObject(type));

        return type;
    }

    public void setType(ResourceType type) {
        Initializer.functionWrite(
                new OutputObject(this),
                "setType",
                OutputObject.generateParamsArray(type)
        );
        Initializer.returnWrite(null);

        this.type = type;
    }

    /**
     *
     * @param resources
     * @param type
     * @return specific recourse or if it's not exist in the array --> null
     */
    public static Resource getResourceByType(ArrayList<Resource> resources, ResourceType type) {
        Initializer.functionWrite(
                new OutputObject(Resource.class),
                "getResourcesByType",
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
}
