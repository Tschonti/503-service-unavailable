package main;

import observables.IObservable;

/**
 * Interface for all things that can be collected by virologists.
 */
public interface Collectable {
    /**
     * Adds this collectable to the inventory.
     * @param inv The inventory that will be getting this collectable.
     */
    void collect(Inventory inv);

    /**
     * Clones this collectable.
     * @return A clone of this collectable.
     */
    Collectable cloneCollectable();

    /**
     * Getter for the view of the collectables
     * @return The view observing this agent
     */
    IObservable getView();
}
