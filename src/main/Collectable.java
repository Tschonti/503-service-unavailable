package main;

public interface Collectable {
    void collect(Inventory inv);
    Collectable clone();
}
