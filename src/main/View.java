package main;

import java.util.ArrayList;

public interface View {
    /**
     * Waits for addig players and all other actions befor starting the game.
     */
    void menu();

    /**
     *
     */
    void chooseAction();

    int chooseOption(ArrayList<String> list);

    void gameOver(Virologist winner);
}
