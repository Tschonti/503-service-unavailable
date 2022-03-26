package main;

import skeleton.Initializer;
import skeleton.OutputObject;

/**
 * Controller
 * It's responsible for controlling the game.
 * It starts the game, manages the rounds and checks for winners.
 */
public class Controller {
    private int activePlayer;
    private Virologist[] players;
    private Map map;
    private GeneticCode[] codes;

    /**
     * Constructor
     */
    public Controller() {
        Initializer.functionWrite(
                new OutputObject(this),
                "constructor",
                null
        );

        activePlayer = 0;
        map = new Map();
        players = new Virologist[20];
        codes = new GeneticCode[20];

        Initializer.returnWrite(new OutputObject(this));
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        Initializer.functionWrite(
                new OutputObject(this),
                "startGame",
                null
        );

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
        Initializer.returnWrite(null);
    }

    /**
     * Starts the next round and calls myTurn() on all players
     */
    private void nextRound() {
        Initializer.functionWrite(
                new OutputObject(this),
                "nextRound",
                null
        );

        for (Virologist player : players) {
            player.myTurn();
        }

        Initializer.returnWrite(null);
    }
}
