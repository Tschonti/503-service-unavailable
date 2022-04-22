package main;

import agents.*;
import equipments.Axe;
import equipments.Bag;
import equipments.Glove;
import equipments.ProtectiveCloak;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import tiles.*;

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
    public Map() {}

    /**
     * Creates a new map.
     * The tiles will be the countries of Europe.
     * @return All the genetic codes that have been generated, these have to be collected by the players
     */
    public GeneticCode[] createMap() {
        ArrayList<Resource> onlyNucleotide = new ArrayList<>();
        onlyNucleotide.add(new Resource(50, ResourceType.Nucleotide));

        GeneticCode[] gcs = new GeneticCode[5];
        gcs[0] = new GeneticCode(6, new StunVirus(), onlyNucleotide);
        gcs[1] = new GeneticCode(23, new VitusDanceVirus(), onlyNucleotide);
        gcs[2] = new GeneticCode(25, new Vaccine(), onlyNucleotide);
        gcs[3] = new GeneticCode(29, new Vaccine(), onlyNucleotide);
        gcs[4] = new GeneticCode(32, new AmnesiaVirus(), onlyNucleotide);

        tiles = new Tile[43];
        tiles[0] = new Warehouse(0, "Albania", new Resource(100, ResourceType.Nucleotide));
        tiles[1] = new EmptyTile(1, "Andorra");
        tiles[2] = new Safehouse(2, "Austria", new Axe());
        tiles[3] = new EmptyTile(3, "Belarus");
        tiles[4] = new EmptyTile(4, "Belgium");
        tiles[5] = new Safehouse(5, "BosniaAndHerzegovina", new Glove());
        tiles[6] = new Laboratory(6, "Bulgaria", gcs[0]);
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
        tiles[23] = new Laboratory(23, "Montenegro", gcs[1]);
        tiles[24] = new EmptyTile(24, "Netherlands");
        tiles[25] = new Laboratory(25, "NorthMacedonia", gcs[2]);
        tiles[26] = new EmptyTile(26, "Norway");
        tiles[27] = new EmptyTile(27, "Poland");
        tiles[28] = new EmptyTile(28, "Portugal");
        tiles[29] = new InfectedLaboratory(29, "Romania", gcs[3], new BearDanceVirus());
        tiles[30] = new EmptyTile(30, "Russia");
        tiles[31] = new EmptyTile(31, "SanMarino");
        tiles[32] = new Laboratory(32, "Serbia", gcs[4]);
        tiles[33] = new EmptyTile(33, "Slovakia");
        tiles[34] = new Safehouse(34, "Slovenia", new Bag());
        tiles[35] = new EmptyTile(35, "Spain");
        tiles[36] = new EmptyTile(36, "Sweden");
        tiles[37] = new EmptyTile(37, "Switzerland");
        tiles[38] = new EmptyTile(38, "Ukraine");
        tiles[39] = new EmptyTile(39, "VaticanCity");
        tiles[40] = new EmptyTile(40, "Ireland");
        tiles[41] = new EmptyTile(41, "UnitedKingdom");
        tiles[42] = new EmptyTile(42, "Kosovo");

        HashMap<Integer, ArrayList<Tile>> borders = new HashMap<>();
        borders.put(0, new ArrayList<>(Arrays.asList(tiles[23], tiles[42], tiles[25], tiles[14])));
        borders.put(1, new ArrayList<>(Arrays.asList(tiles[12], tiles[35])));
        borders.put(
            2,
            new ArrayList<>(
                Arrays.asList(
                    tiles[15],
                    tiles[33],
                    tiles[8],
                    tiles[13],
                    tiles[37],
                    tiles[18],
                    tiles[16],
                    tiles[34]
                )
            )
        );
        borders.put(
            3,
            new ArrayList<>(Arrays.asList(tiles[30], tiles[17], tiles[19], tiles[27], tiles[38]))
        );
        borders.put(4, new ArrayList<>(Arrays.asList(tiles[24], tiles[12], tiles[20], tiles[13])));
        borders.put(5, new ArrayList<>(Arrays.asList(tiles[32], tiles[23], tiles[7])));
        borders.put(6, new ArrayList<>(Arrays.asList(tiles[14], tiles[25], tiles[32], tiles[29])));
        borders.put(
            7,
            new ArrayList<>(Arrays.asList(tiles[32], tiles[5], tiles[23], tiles[34], tiles[15]))
        );
        borders.put(8, new ArrayList<>(Arrays.asList(tiles[27], tiles[33], tiles[2], tiles[13])));
        borders.put(9, new ArrayList<>(Arrays.asList(tiles[36], tiles[13])));
        borders.put(10, new ArrayList<>(Arrays.asList(tiles[30], tiles[17])));
        borders.put(11, new ArrayList<>(Arrays.asList(tiles[30], tiles[36], tiles[26])));
        borders.put(
            12,
            new ArrayList<>(
                Arrays.asList(
                    tiles[4],
                    tiles[20],
                    tiles[13],
                    tiles[37],
                    tiles[16],
                    tiles[22],
                    tiles[35],
                    tiles[1],
                    tiles[41]
                )
            )
        );
        borders.put(
            13,
            new ArrayList<>(
                Arrays.asList(
                    tiles[27],
                    tiles[8],
                    tiles[2],
                    tiles[37],
                    tiles[12],
                    tiles[20],
                    tiles[4],
                    tiles[24],
                    tiles[9]
                )
            )
        );
        borders.put(14, new ArrayList<>(Arrays.asList(tiles[0], tiles[25], tiles[6])));
        borders.put(
            15,
            new ArrayList<>(
                Arrays.asList(
                    tiles[38],
                    tiles[29],
                    tiles[32],
                    tiles[7],
                    tiles[34],
                    tiles[2],
                    tiles[33]
                )
            )
        );
        borders.put(
            16,
            new ArrayList<>(
                Arrays.asList(tiles[34], tiles[31], tiles[39], tiles[12], tiles[37], tiles[2])
            )
        );
        borders.put(17, new ArrayList<>(Arrays.asList(tiles[30], tiles[19], tiles[10])));
        borders.put(18, new ArrayList<>(Arrays.asList(tiles[2], tiles[37])));
        borders.put(19, new ArrayList<>(Arrays.asList(tiles[3], tiles[27], tiles[30], tiles[17])));
        borders.put(20, new ArrayList<>(Arrays.asList(tiles[13], tiles[12], tiles[4])));
        borders.put(21, new ArrayList<>(Arrays.asList(tiles[38], tiles[29])));
        borders.put(22, new ArrayList<>(Collections.singletonList(tiles[12])));
        borders.put(
            23,
            new ArrayList<>(Arrays.asList(tiles[32], tiles[42], tiles[0], tiles[7], tiles[5]))
        );
        borders.put(24, new ArrayList<>(Arrays.asList(tiles[13], tiles[4])));
        borders.put(
            25,
            new ArrayList<>(Arrays.asList(tiles[6], tiles[14], tiles[0], tiles[42], tiles[32]))
        );
        borders.put(26, new ArrayList<>(Arrays.asList(tiles[30], tiles[11], tiles[36])));
        borders.put(
            27,
            new ArrayList<>(
                Arrays.asList(
                    tiles[19],
                    tiles[3],
                    tiles[38],
                    tiles[33],
                    tiles[8],
                    tiles[13],
                    tiles[30]
                )
            )
        );
        borders.put(28, new ArrayList<>(Collections.singletonList(tiles[35])));
        borders.put(
            29,
            new ArrayList<>(Arrays.asList(tiles[6], tiles[21], tiles[38], tiles[32], tiles[15]))
        );
        borders.put(
            30,
            new ArrayList<>(
                Arrays.asList(
                    tiles[38],
                    tiles[3],
                    tiles[19],
                    tiles[27],
                    tiles[17],
                    tiles[10],
                    tiles[11],
                    tiles[26]
                )
            )
        );
        borders.put(31, new ArrayList<>(Collections.singletonList(tiles[16])));
        borders.put(
            32,
            new ArrayList<>(
                Arrays.asList(
                    tiles[29],
                    tiles[6],
                    tiles[25],
                    tiles[42],
                    tiles[23],
                    tiles[5],
                    tiles[7],
                    tiles[15]
                )
            )
        );
        borders.put(
            33,
            new ArrayList<>(Arrays.asList(tiles[38], tiles[15], tiles[2], tiles[8], tiles[27]))
        );
        borders.put(34, new ArrayList<>(Arrays.asList(tiles[15], tiles[7], tiles[16], tiles[2])));
        borders.put(35, new ArrayList<>(Arrays.asList(tiles[28], tiles[12], tiles[1])));
        borders.put(36, new ArrayList<>(Arrays.asList(tiles[11], tiles[9], tiles[26])));
        borders.put(
            37,
            new ArrayList<>(Arrays.asList(tiles[2], tiles[18], tiles[16], tiles[12], tiles[13]))
        );
        borders.put(
            38,
            new ArrayList<>(
                Arrays.asList(
                    tiles[30],
                    tiles[21],
                    tiles[29],
                    tiles[15],
                    tiles[33],
                    tiles[27],
                    tiles[3]
                )
            )
        );
        borders.put(39, new ArrayList<>(Collections.singletonList(tiles[16])));
        borders.put(40, new ArrayList<>(Collections.singletonList(tiles[41])));
        borders.put(41, new ArrayList<>(Arrays.asList(tiles[12], tiles[40])));
        borders.put(42, new ArrayList<>(Arrays.asList(tiles[32], tiles[25], tiles[0], tiles[23])));

        borders.forEach((key, value) -> value.forEach(tile -> tiles[key].addNeighbour(tile)));
        return gcs;
    }

    /**
     * Gets the tile by the given name.
     * @param name Name of the tile to get.
     * @return tile
     */
    Tile getTile(String name) {
        for (Tile tile : tiles) {
            if (tile.getName().equalsIgnoreCase(name)) {
                return tile;
            }
        }
        return null;
    }
}
