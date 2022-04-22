package main;

public interface View {
    /**
     * Menu loop of the game.
     * Waits for adding players and all other actions before starting the game.
     */
    void menu();

    /**
     * Method for handling player inputs.
     */
    void chooseAction();

    /**
     * Method for the end of the game.
     * @param winner The winner of the game.
     */
    void gameOver(Virologist winner);
}
