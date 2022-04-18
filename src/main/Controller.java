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

    private boolean isWinner;

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
        endOfGame = false;
        isWinner = false;
    }

    /**
     *
     */
    public void gameLoop() {
        endOfGame = players.isEmpty();
        while(!endOfGame) {
            for (Virologist player : players) {
                activePlayer = player;
                activePlayer.startTurn();
                while (activePlayer.getActionsLeft() > 0)  {
                    view.chooseAction();
                    if (endOfGame) {
                        break;
                    }
                }
                if (!endOfGame) {
                    activePlayer.endTurn();
                }
            }
        }
        if (isWinner) {
            view.gameOver(activePlayer);
        }
        activePlayer = null;
        players.clear();
        endOfGame = false;
        isWinner = false;
    }

    /**
     * Checks, if this player has collected all the genetic codes.
     * @param v The virologist (player) we check.
     */
    public void checkWinner(Virologist v) {
        if(v.getInventory().getLearntCodes().size() == codes.length - 1) { //TODO az a -1 csak a medve miatt van
            endOfGame = true;
            isWinner = true;
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
            if (player.getName().equalsIgnoreCase(name)) {
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
        String existsMessage = "Player with that name already exists!";
        try {
            getPlayerByName(v.getName());
            throw new IllegalArgumentException(existsMessage);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals(existsMessage)) {
                throw e;
            }
        }
        Tile tile = getTileByName(tileName);
        tile.addVirologist(v);
        players.add(v);
        v.setActiveTile(tile);
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
        if (activePlayer.getInventory().getUsableEquipments().contains(ue)) {
            if (activePlayer.getNearbyVirologists().contains(v)) {
                ue.use(activePlayer, v);
            } else {
                throw new IllegalArgumentException("You can't use this UsableEquipment on " + v.getName() + "!");
            }
        } else {
            throw new IllegalArgumentException("You don't have this UsableEquipment!");
        }
    }

    public void steal(Virologist v, Equipment eq) {
        if (v.getNearbyVirologistsToStealFrom().contains(v)) {
            activePlayer.steal(v, eq);
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
        isWinner = false;
        activePlayer = null;
    }

    public void quit() {
        System.exit(0);
    }

    public Virologist getActivePlayer(){
        return activePlayer;
    }

    public ArrayList<Virologist> getPlayers() {
        return players;
    }
}
