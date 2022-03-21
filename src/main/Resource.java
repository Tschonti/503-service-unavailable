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
        Initializer.returnWrite(null);

        amount = a;
        type = t;
    }

    public void collect(Inventory inv) {
        Initializer.functionWrite(
                new OutputObject(this),
                "collect",
                OutputObject.generateParamsArray(inv)
        );
        Initializer.returnWrite(null);
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

    public static Resource getResourceByType(ArrayList<Resource> resources, ResourceType type) {
        Initializer.functionWrite(
                new OutputObject(Resource.class),
                "getResourcesByType",
                OutputObject.generateParamsArray(resources, type)
        );
        //TODO:
        Initializer.returnWrite(new OutputObject(new Resource(0, ResourceType.Nucleotid)));

        //TODO:
        return new Resource(0, ResourceType.Nucleotid);
    }
}
