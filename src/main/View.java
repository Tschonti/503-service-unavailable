package main;

public interface View {
    /**
     * Menu loop of the game.
     * Waits for adding players and all other actions before starting the game.
     */
    void menu();

    /**
     *
     */
    void chooseAction();

    /**
     *
     * @param winner
     */
    void gameOver(Virologist winner);
}
