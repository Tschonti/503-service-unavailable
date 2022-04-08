package main;

import java.util.ArrayList;

public interface View {
    /**
     * Waits for adding players and all other actions before starting the game.
     */
    void menu();

    void chooseAction();

    int chooseOption(ArrayList<String> list);

    void gameOver(Virologist winner);
}
