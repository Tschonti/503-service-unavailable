package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleView implements View {
    Controller controller = new Controller(this);
    private HashMap<String, Command> menu;
    private HashMap<String, Command> actions;
    private String[] commandlist;
    private final Scanner scanner = new Scanner(System.in);
    private boolean quitMenu = false;
    private boolean quitGame = false;

    public ConsoleView(){
        menu=new HashMap<>();
        menu.put("start", this::chooseAction);
        menu.put("add", ConsoleView::add);
        menu.put("quit", ()->quitMenu=true);

        actions=new HashMap<>();
        actions.put("collect", ConsoleView::collect);
        actions.put("move", ConsoleView::move);
        actions.put("use", ConsoleView::use);
        actions.put("craft", ConsoleView::craft);
        actions.put("drop", ConsoleView::drop);
        actions.put("info", ConsoleView::info);
        if(Main.getDebugMode()){
            actions.put("setnextrandom", ConsoleView::setNextRandom);
        }
        actions.put("quit", ()->quitGame=true);
    }


    public interface Command {
        /**
         * Starts the testcase.
         */
        void run();
    }
    public void menu() {
        while(!quitMenu){
            try {
                commandlist = scanner.nextLine().split(" ");
                menu.get(commandlist[0]).run();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }

    public void chooseAction() {
        while(!quitGame){
            try {
                commandlist = scanner.nextLine().split(" ");
                actions.get(commandlist[0]).run();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    public int chooseOption(ArrayList<String> list) {
        return 0;
    }

    public void gameOver(Virologist winner) {
        System.out.println("End of the game, winner: " + winner.getName());
    }

    public static void add() {

    }

    public static void collect() {

    }

    private static void move() {
    }

    private static void use(){

    }

    private static void craft(){

    }

    private static void drop(){

    }

    private static void info(){

    }

    private static void setNextRandom(){

    }

}


