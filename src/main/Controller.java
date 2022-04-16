package main;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
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

    private final View view;

    /**
     * Constructor
     */
    public Controller(View newView) {
        activePlayer = null;
        map = new Map();
        players = new ArrayList<>();
        codes = map.createMap();
        view = newView;
    }


    /**
     *
     */
    public void gameLoop() {
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
    }

    /**
     * Checks, if this player has collected all the genetic codes.
     * @param v The virologist (player) we check.
     */
    public void checkWinner(Virologist v) {
        if(activePlayer.getInventory().getLearntCodes().size() == codes.length) {
            endOfGame = true;
        }
    }

    /**
     * Removes a player from the players.
     * @param v The new player.
     */
    public void removeVirologist(Virologist v) {
        players.remove(v);
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


    //**********************************command functions**********************************
    /**
     * Adds a player to the players.
     * @param v The new player.
     */
    public void addPlayer(Virologist v, String tileName) {
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
    }

    public void move(Tile t) {
        if (activePlayer.getNeighbours().contains(t)) {
            activePlayer.moveTo(t);
        } else {
            throw new IllegalArgumentException("You can't move to this tile!");
        }
    }

    public void collect() {
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

    public void use(UsableEquipment ue, Virologist v) {
        //TODO előzőhöz hasonló, csak siker esetén meg kell ölni v-t
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

    public void quit() {
        System.exit(0);
    }

    public Virologist getActivePlayer(){
        return activePlayer;
    }
}
