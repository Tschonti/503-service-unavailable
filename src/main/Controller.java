package main;

import skeleton.Initializer;
import skeleton.OutputObject;

public class Controller {
    private int activePlayer;
    private Virologist[] players;
    private Map map;
    private GeneticCode[] codes;

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

    public void startGame() {
        Initializer.functionWrite(
                new OutputObject(this),
                "startGame",
                null
        );

        map.createMap();

        Initializer.returnWrite(new OutputObject(this));
    }

    public void checkWinner(Virologist v) {
        Initializer.functionWrite(
                new OutputObject(this),
                "checkWinner",
                OutputObject.generateParamsArray(v)
        );
        Initializer.returnWrite(null);
    }

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
