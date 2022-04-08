package main;

import agents.Agent;
import equipments.Equipment;
import skeleton.Initializer;
import skeleton.OutputObject;
import tiles.Tile;

import java.util.ArrayList;

/**
 * It's responsible for controlling the game.
 * It starts the game, manages the rounds and checks for winners.
 */
public class Controller {

    /**
     * The player, that is active.
     */
    private Virologist activePlayer;

    /**
     * All the players in the game.
     */
    private final ArrayList<Virologist> players;

    /**
     * The map of the game.
     */
    private final Map map;

    /**
     * All the genetic codes in the game.
     */
    private final GeneticCode[] codes;

    private boolean endOfGame;

    private View view;

    /**
     * Constructor
     */
    public Controller(View newView) {
        Initializer.functionWrite(new OutputObject(this), "constructor", null);

        activePlayer = null;
        map = new Map();
        players = new ArrayList<>();
        codes = map.createMap();
        view = newView;

        Initializer.returnWrite(null);
    }


    /**
     * Calls myTurn() on all players.
     */
    private void gameLoop() {
        Initializer.functionWrite(new OutputObject(this), "nextRound", null);

        while(!endOfGame) {
            for (Virologist player : players) {
                activePlayer = player;
                activePlayer.startTurn();
                while (activePlayer.getActionsLeft() > 0)  {
                    view.chooseAction();
                }
                activePlayer.endTurn();
            }
        }

        view.gameOver(activePlayer);

        Initializer.returnWrite(null);
    }

    /**
     * Checks, if this player has collected all the genetic codes.
     * @param v The virologist (player) we check.
     */
    public void checkWinner(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "checkWinner",
                OutputObject.generateParamsArray(v)
        );

        if(activePlayer.getInventory().getLearntCodes().size() == codes.length) {
            endOfGame = true;
        }

        if (Initializer.questionYesOrNo("Was this the last genetic code to collect?")) {
            System.out.println(v.getName() + " is the winner!");
            System.exit(0);
        }
        Initializer.returnWrite(null);
    }

    /**
     * Removes a player from the players.
     * @param v The new player.
     */
    public void removeVirologist(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "removeVirologist",
                OutputObject.generateParamsArray(v)
        );
        players.remove(v);
        Initializer.returnWrite(null);
    }


    public Virologist getPlayerByName(String name) {
        for (Virologist player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        throw new IllegalArgumentException("There is no player named " + name);
    }

    public Tile getTileByName(String name) {
        Tile t = map.getTile(name);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("There is no tile called " + name);
    }


    //**********************************parancs függvények**********************************
    /**
     * Adds a player to the players.
     * @param v The new player.
     */
    public void addPlayer(Virologist v, String tileName) {
        Initializer.functionWrite(
                new OutputObject(this),
                "addPlayer",
                OutputObject.generateParamsArray(v)
        );

        if (players.contains(v)) {
            throw new IllegalArgumentException("Player is already in the game!");
        }

        Tile tile = map.getTile(tileName);
        if (tile != null) {
            tile.addVirologist(v);
        } else {
            throw new IllegalArgumentException("Tile doesn't exist!");
        }
        players.add(v);

        Initializer.returnWrite(null);
    }


    public void move(Tile t) {
        if (activePlayer.getNeighbours().contains(t)) {
            activePlayer.moveTo(t);
        } else {
            throw new IllegalArgumentException("You can't move to this tile!");
        }
    }

    public void collect() { //checkwinner
        if (activePlayer.getActiveTile().getCollectableItem() != null) {
            activePlayer.pickUp();
            checkWinner(activePlayer);
        } else {
            throw new IllegalArgumentException("There is nothing to collect in this tile");
        }
    }

    public void craft(GeneticCode code) {
        if (code.isCraftable(activePlayer.getInventory())) {
            activePlayer.craft(code);
        } else {
            throw new IllegalArgumentException("You can't craft this!");
        }
    }

    public void use(Agent agent, Virologist v) {
        if (activePlayer.getCraftedAgents().contains(agent)) {
            if (activePlayer.getNearbyVirologists().contains(v)) {
                activePlayer.useAgent(agent, v);
            } else {
                throw new IllegalArgumentException("You can't use this agent on " + v.getName() + "!");
            }
        } else {
            throw new IllegalArgumentException("You don't have this agent!");
        }
    }

    public void steal(Virologist v) {
        if (v.getNearbyVirologistsToStealFrom().contains(v)) {
            activePlayer.steal(v);
        } else {
            throw new IllegalArgumentException("You can't steal from " + v.getName() +"!");
        }
    }

    public void drop(Equipment eq) {
        if (activePlayer.getInventory().getEquipments().contains(eq)) {
            activePlayer.drop(eq);
        } else {
            throw new IllegalArgumentException("You don't have this equipment!");
        }
    }

    public void pass() {
        activePlayer.pass();
    }

    public void endGame() {
        endOfGame = true;
        activePlayer = null;
    }

    public  void quit() {
        System.exit(0);
    }
}
