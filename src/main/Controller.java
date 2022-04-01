package main;

import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * It's responsible for controlling the game.
 * It starts the game, manages the rounds and checks for winners.
 */
public class Controller {

    /**
     * The player, that is active.
     */
    private int activePlayer;

    /**
     * All the players in the game.
     */
    private final Virologist[] players;

    /**
     * The map of the game.
     */
    private final Map map;

    /**
     * All the genetic codes in the game.
     */
    private final GeneticCode[] codes;

    /**
     * Constructor
     */
    public Controller() {
        Initializer.functionWrite(new OutputObject(this), "constructor", null);

        activePlayer = 0;
        map = new Map();
        players = new Virologist[20];
        codes = new GeneticCode[20];

        Initializer.returnWrite(null);
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        Initializer.functionWrite(new OutputObject(this), "startGame", null);

        map.createMap();

        Initializer.returnWrite(new OutputObject(this));
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
        if (Initializer.questionYesOrNo("Was this the last genetic code to collect?")) {
            System.out.println(v.getName() + " is the winner!");
            System.exit(0);
        }
        Initializer.returnWrite(null);
    }

    /**
     * Starts the next round and calls myTurn() on all players.
     */
    private void nextRound() {
        Initializer.functionWrite(new OutputObject(this), "nextRound", null);

        for (Virologist player : players) {
            player.myTurn();
        }

        Initializer.returnWrite(null);
    }

    /**
     * Adds a player to the players.
     * @param v The new player.
     */
    public void addPlayer(Virologist v) {
        Initializer.functionWrite(
            new OutputObject(this),
            "addPlayer",
            OutputObject.generateParamsArray(v)
        );
        players[0] = v;
        Initializer.returnWrite(null);
    }
}
