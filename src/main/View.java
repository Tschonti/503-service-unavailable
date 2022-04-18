package main;

public interface View {
    /**
     * Waits for adding players and all other actions before starting the game.
     */
    void menu();

    void chooseAction();

    void gameOver(Virologist winner);
}
