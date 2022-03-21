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
        Initializer.returnWrite(new OutputObject(this));
    }

    public void startGame() {
        Initializer.functionWrite(
                new OutputObject(this),
                "startGame",
                null
        );
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
        Initializer.returnWrite(null);
    }
}
