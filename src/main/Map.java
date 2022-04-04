package main;

import agents.AmnesiaVirus;
import agents.StunVirus;
import agents.Vaccine;
import agents.VitusDanceVirus;
import equipments.Axe;
import equipments.Bag;
import equipments.Glove;
import equipments.ProtectiveCloak;
import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The map of the game.
 * It can create a map and store its tiles.
 */
public class Map {

    /**
     * Tiles of the map.
     */
    private Tile[] tiles;

    /**
     * Constructor
     */
    public Map() {
        Initializer.functionWrite(new OutputObject(this), "constructor", null);
        Initializer.returnWrite(null);
    }

    /**
     * Creates a new map.
     */
    public void createMap() {
        Initializer.functionWrite(new OutputObject(this), "createMap", null);
        tiles = new Tile[38];
        tiles[0] = new Warehouse(0, "Albania", new Resource(100, ResourceType.Nucleotide));
        tiles[1] = new EmptyTile(1, "Andorra");
        tiles[2] = new Safehouse(2, "Austria", new Axe());
        tiles[3] = new EmptyTile(3, "Belarus");
        tiles[4] = new EmptyTile(4, "Belgium");
        tiles[5] = new Safehouse(5, "BosniaAndHerzegovina", new Glove());
        tiles[6] = new Laboratory(6, "Bulgaria", new StunVirus());
        tiles[7] = new Safehouse(7, "Croatia", new ProtectiveCloak());
        tiles[8] = new EmptyTile(8, "Czechia");
        tiles[9] = new EmptyTile(9, "Denmark");
        tiles[10] = new EmptyTile(10, "Estonia");
        tiles[11] = new EmptyTile(11, "Finland");
        tiles[12] = new EmptyTile(12, "France");
        tiles[13] = new EmptyTile(13, "Germany");
        tiles[14] = new Warehouse(14, "Greece", new Resource(100, ResourceType.AminoAcid));
        tiles[15] = new EmptyTile(15, "Hungary");
        tiles[16] = new EmptyTile(16, "Italy");
        tiles[17] = new EmptyTile(17, "Latvia");
        tiles[18] = new EmptyTile(18, "Liechtenstein");
        tiles[19] = new EmptyTile(19, "Lithuania");
        tiles[20] = new EmptyTile(20, "Luxembourg");
        tiles[21] = new EmptyTile(21, "Moldova");
        tiles[22] = new EmptyTile(22, "Monaco");
        tiles[23] = new Laboratory(23, "Montenegro", new VitusDanceVirus());
        tiles[24] = new EmptyTile(24, "Netherlands");
        tiles[25] = new Laboratory(25, "NorthMacedonia", new Vaccine());
        tiles[26] = new EmptyTile(26, "Norway");
        tiles[27] = new EmptyTile(27, "Poland");
        tiles[28] = new EmptyTile(28, "Portugal");
        tiles[29] = new InfectedLaboratory(29, "Romania", new Vaccine());
        tiles[30] = new EmptyTile(30, "Russia");
        tiles[31] = new EmptyTile(31, "SanMarino");
        tiles[32] = new Laboratory(32, "Serbia", new AmnesiaVirus());
        tiles[33] = new EmptyTile(33, "Slovakia");
        tiles[34] = new Safehouse(34, "Slovenia", new Bag());
        tiles[35] = new EmptyTile(35, "Spain");
        tiles[36] = new EmptyTile(36, "Sweden");
        tiles[37] = new EmptyTile(37, "Switzerland");
        tiles[38] = new EmptyTile(38, "Ukraine");
        tiles[39] = new EmptyTile(39, "VaticanCity");
        tiles[40] = new EmptyTile(40, "Ireland");
        tiles[41] = new EmptyTile(41, "UnitedKingdom");

        HashMap<Integer, ArrayList<Tile>> borders = new HashMap<>();
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));
        borders.put(1, new ArrayList<>(Arrays.asList(tiles[12], tiles[35])));
        borders.put(2, new ArrayList<>(Arrays.asList(tiles[15], tiles[33], tiles[8], tiles[13], tiles[37], tiles[18], tiles[16], tiles[33])));
        borders.put(3, new ArrayList<>(Arrays.asList(tiles[30], tiles[17], tiles[19], tiles[27], tiles[38])));
        borders.put(4, new ArrayList<>(Arrays.asList(tiles[24], tiles[12], tiles[20], tiles[13])));


        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[32], tiles[25], tiles[14])));



        Initializer.returnWrite(null);
    }

}
