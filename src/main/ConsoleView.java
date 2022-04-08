package main;

import java.util.ArrayList;

public class ConsoleView implements View {

    Controller controller = new Controller(this);

    @Override
    public void menu() {

    }

    @Override
    public void chooseAction() {

    }

    @Override
    public int chooseOption(ArrayList<String> list) {
        return 0;
    }

    @Override
    public void gameOver(Virologist winner) {
        System.out.println("End of the game, winner: " + winner.getName());
    }
}
