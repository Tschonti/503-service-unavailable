package main;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
import java.util.ArrayList;
import tiles.Tile;

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
     * All the players in the game.
     */
    private final ArrayList<Virologist> infectedPlayers;

    /**
     * The map of the game.
     */
    private final Map map;

    /**
     * All the genetic codes in the game.
     */
    private GeneticCode[] codes;

    /**
     * True, if the game is over.
     */
    private boolean endOfGame;

    /**
     * The view for the game.
     */
    private final GraphicsView view;

    /**
     * Constructor
     */
    public Controller(GraphicsView newView) {
        activePlayer = null;
        map = new Map();
        players = new ArrayList<>();
        infectedPlayers = new ArrayList<>();
        codes = map.createMap();
        view = newView;
        endOfGame = false;
    }

    /**
     * Initializes the game.
     */
    public void gameStart() {
        endOfGame = players.isEmpty();
        activePlayer = players.get(0);
        activePlayer.startTurn();
    }

    public void actionHappened(){
        if (players.size() == infectedPlayers.size()) {
            endOfGame = true;
            activePlayer = null;
        }
        if(endOfGame){
            view.gameOver(activePlayer);
            return;
        }
        while(activePlayer.getActionsLeft()<=0){
            activePlayer.endTurn();
            activePlayer = players.get((players.indexOf(activePlayer) + 1) % players.size());
            activePlayer.startTurn();
            if (players.size() == infectedPlayers.size()) {
                view.gameOver(null);
                return;
            }
        }
        view.Paint();
    }
    /**
     * Checks, if this player has collected all the genetic codes.
     * @param v The virologist (player) we check.
     */
    public void checkWinner(Virologist v) {
        if (v.getInventory().getLearntCodes().size() == codes.length) {
            endOfGame = true;
        }
    }

    /**
     * Removes a player from the players.
     * @param v The new player.
     */
    public void removeVirologist(Virologist v) {
        players.remove(v);
        if (isInfected(v)) {
            infectedPlayers.remove(v);
        }
    }

    /**
     * Gets the player with the given parameter name.
     * @param name Name of the player.
     * @return player
     */
    public Virologist getPlayerByName(String name) {
        for (Virologist player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        throw new IllegalArgumentException("There is no player named " + name);
    }

    /**
     * Gets the tile with given parameter name.
     * @param name Name of the tile.
     * @return tile
     */
    public Tile getTileByName(String name) {
        Tile t = map.getTile(name);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("There is no tile called " + name);
    }

    /**
     * Getter for activePlayer.
     * @return activePlayer
     */
    public Virologist getActivePlayer() {
        return activePlayer;
    }

    /**
     * Getter for players.
     * @return players
     */
    public ArrayList<Virologist> getPlayers() {
        return players;
    }

    /**
     * Adds a new player to the game.
     * @param v The new player.
     * @param tileName The name of the starting tile.
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
        v.setActiveTile(tile);
        tile.addVirologist(v);
        players.add(v);
    }

    /**
     * Moves the player to another tile.
     * @param t tile
     */
    public void move(Tile t) {
        if (activePlayer.getNeighbours().contains(t)) {
            activePlayer.moveTo(t);
        } else {
            throw new IllegalArgumentException("You can't move to this tile!");
        }
        actionHappened();
    }

    /**
     * Collects the contents of the tile by the player.
     */
    public void collect() {
        if (activePlayer.getActiveTile().getCollectableItem() != null) {
            activePlayer.pickUp();
            checkWinner(activePlayer);
        } else {
            throw new IllegalArgumentException("There is nothing to collect in this tile");
        }
        actionHappened();
    }

    /**
     * Crafts the agent of the given genetic code by the player.
     * @param code Genetic code to craft from.
     */
    public void craft(GeneticCode code) {
        if (code.isCraftable(activePlayer.getInventory())) {
            activePlayer.craft(code);
        } else {
            throw new IllegalArgumentException("You can't craft this!");
        }
        actionHappened();
    }

    /**
     * Uses the given agent on the given player.
     * @param agent The agent to use.
     * @param v The player to use the agent on.
     */
    public void use(Agent agent, Virologist v) {
        if (activePlayer.getCraftedAgents().contains(agent)) {
            if (activePlayer.getActiveTile().getPlayers().contains(v)) {
                activePlayer.useAgent(agent, v);

            } else {
                throw new IllegalArgumentException(
                    "You can't use this agent on " + v.getName() + "!"
                );
            }
        } else {
            throw new IllegalArgumentException("You don't have this agent!");
        }
        actionHappened();
    }

    /**
     * Uses the given equipment on the given player.
     * @param ue The equipment to use.
     * @param v The player to use the equipment on.
     */
    public void use(UsableEquipment ue, Virologist v) {
        if (activePlayer.getInventory().getUsableEquipments().contains(ue)) {
            if (activePlayer.getNearbyVirologists().contains(v)) {
                ue.use(activePlayer, v);
            } else {
                throw new IllegalArgumentException(
                    "You can't use this UsableEquipment on " + v.getName() + "!"
                );
            }
        } else {
            throw new IllegalArgumentException("You don't have this UsableEquipment!");
        }
        actionHappened();
    }

    /**
     * Steals the given equipment from the given player.
     * @param v The player to steal from.
     * @param eq The equipment to be stolen.
     */
    public void steal(Virologist v, Equipment eq) {
        if (activePlayer.getNearbyVirologistsToStealFrom().contains(v)) {
            activePlayer.steal(v, eq);
        } else {
            throw new IllegalArgumentException("You can't steal from " + v.getName() + "!");
        }
        actionHappened();
    }

    /**
     * Drops the given equipment.
     * @param eq The equipment to drop.
     */
    public void drop(Equipment eq) {
        if (activePlayer.getInventory().getEquipments().contains(eq)) {
            activePlayer.drop(eq);
        } else {
            throw new IllegalArgumentException("You don't have this equipment!");
        }
        actionHappened();
    }

    /**
     * Passes an action.
     */
    public void pass() {
        activePlayer.pass();
        actionHappened();
    }

    /**
     * Force-ends the game, without anyone winning.
     */
    public void endGame() {
        endOfGame = false;
        activePlayer = null;
        players.clear();
        infectedPlayers.clear();
        codes = map.createMap();
    }

    /**
     * Adds the virologist to the infected players.
     * @param v virologist
     */
    public void addInfected(Virologist v) {
        infectedPlayers.add(v);
    }

    /**
     * Returns true, if the virologist is infected.
     * @param v virologist
     * @return whether the virologist is infected
     */
    public boolean isInfected(Virologist v) {
        return infectedPlayers.contains(v);
    }
}
