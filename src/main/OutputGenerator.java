package main;

import tiles.Tile;

/**
 * A class with methods that generate a string for the info command.
 * There's a method for each info item.
 */
public class OutputGenerator {

    public interface VirologistInfoItem {
        String generate(Virologist v);
    }

    public static String generateName(Virologist v) {
        return "Name:\n\t" + v.getName() + "\n";
    }

    public static String generateActionsLeft(Virologist v) {
        return "Actions left:\n\t" + v.getActionsLeft() + "/2\n";
    }

    public static String generateTile(Virologist v) {
        return "Tile:\n\t" + v.getActiveTile() + "\n";
    }

    public static String generateNeighbours(Virologist v) {
        StringBuilder s = new StringBuilder("Neighbour tiles:\n");
        v.getNeighbours().forEach(t -> s.append("\t").append(t).append("\n"));
        return s.toString();
    }

    public static String generateVirologistsOnTile(Virologist v) {
        StringBuilder s = new StringBuilder("Virologists on the same Tile:\n");
        v
            .getActiveTile()
            .getPlayers()
            .forEach(vir -> {
                if (vir != v) {
                    s.append("\t").append(vir.getName()).append("\n");
                }
            });
        return s.toString();
    }

    public static String generateStunnedVirologists(Virologist v) {
        StringBuilder s = new StringBuilder("Stunned Virologists:\n");
        v
            .getNearbyVirologistsToStealFrom()
            .forEach(vir -> s.append("\t").append(vir.getName()).append("\n"));
        return s.toString();
    }

    public static String generateCollectable(Virologist v) {
        Collectable c = v.getActiveTile().getCollectableItem();
        StringBuilder s = new StringBuilder("Collectable:\n");
        if (c != null) {
            s.append("\t").append(c).append("\n");
        }
        return s.toString();
    }

    public static String generateResources(Virologist v) {
        StringBuilder s = new StringBuilder("Resources:\n");
        v
            .getInventory()
            .getResources()
            .forEach(r ->
                s
                    .append("\t")
                    .append(r)
                    .append("/")
                    .append(v.getInventory().getMaxResourceAmount())
                    .append("\n")
            );
        return s.toString();
    }

    public static String generateGeneticCodes(Virologist v) {
        StringBuilder s = new StringBuilder("Genetic codes:\n");
        v.getInventory().getLearntCodes().forEach(c -> s.append("\t").append(c).append("\n"));
        return s.toString();
    }

    public static String generateUsables(Virologist v) {
        StringBuilder s = new StringBuilder("Usables:\n");
        v.getInventory().getCraftedAgents().forEach(a -> s.append("\t").append(a).append("\n"));
        v.getInventory().getUsableEquipments().forEach(e -> s.append("\t").append(e).append("\n"));
        return s.toString();
    }

    public static String generateEquipments(Virologist v) {
        StringBuilder s = new StringBuilder("Equipments:\n");
        v.getInventory().getEquipments().forEach(e -> s.append("\t").append(e).append("\n"));
        return s.toString();
    }

    public static String generateEffects(Virologist v) {
        StringBuilder s = new StringBuilder("Effects:\n");
        v.getActiveEffects().forEach(e -> s.append("\t").append(e).append("\n"));
        return s.toString();
    }

    public interface TileInfoItem {
        String generate(Tile t);
    }

    public static String generateName(Tile t) {
        return "Name:\n\t" + t.getName() + "\n";
    }

    public static String generateID(Tile t) {
        return "ID:\n\t" + t.getId() + "\n";
    }

    public static String generateType(Tile t) {
        return "Type:\n\t" + t.toString().split("@")[0] + "\n";
    }

    public static String generateNeighbours(Tile t) {
        StringBuilder s = new StringBuilder("Neighbours:\n");
        t.getNeighbours().forEach(tile -> s.append("\t").append(tile).append("\n"));
        return s.toString();
    }

    public static String generateCollectable(Tile t) {
        Collectable c = t.getCollectableItem();
        StringBuilder s = new StringBuilder("Collectable:\n");
        if (c != null) {
            s.append("\t").append(c).append("\n");
        }
        return s.toString();
    }
}
