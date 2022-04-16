package main;

import agents.Agent;
import equipments.Equipment;
import tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleView implements View {
    static Controller controller;
    private HashMap<String, Command> menu;
    private HashMap<String, Command> actions;
    private static String[] commandList;
    private final Scanner scanner = new Scanner(System.in);
    private boolean quitMenu = false;
    private boolean quitGame = false;

    public ConsoleView(){
        controller = new Controller(this);
        menu=new HashMap<>();
        menu.put("start", controller::gameLoop);
        menu.put("add", ConsoleView::add);
        menu.put("quit", ()->quitMenu=true);

        actions=new HashMap<>();
        actions.put("collect", ConsoleView::collect);
        actions.put("move", ConsoleView::move);
        actions.put("use", ConsoleView::use);
        actions.put("craft", ConsoleView::craft);
        actions.put("steal", ConsoleView::steal);
        actions.put("drop", ConsoleView::drop);
        actions.put("pass", ConsoleView::pass);
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
                commandList = scanner.nextLine().split(" ");
                menu.get(commandList[0].toLowerCase()).run();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void chooseAction() {
        while(!quitGame){
            try {
                commandList = scanner.nextLine().split(" ");
                actions.get(commandList[0]).run();
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
        if(commandList.length>3){
            throw new IllegalArgumentException("Too many arguments");
        }
        if(commandList.length<2){
            throw new IllegalArgumentException("Not enough arguments");
        }

        if(commandList.length==3){
            Virologist virologist=new Virologist(commandList[1]);
            controller.addPlayer(virologist, commandList[2]);
        }
        else{
            Virologist virologist=new Virologist(commandList[1]);
            controller.addPlayer(virologist, "Hungary");//TODO ez igy jo hogy fix mezore kerul?
        }
    }

    private static void collect() {
        controller.collect();
    }

    private static void move() {
        if(commandList.length>2){
            throw new IllegalArgumentException("Too many arguments");
        }
        Tile tile=controller.getTileByName(commandList[1]);
        controller.move(tile);
    }

    private static void use() {
        //TODO a balta az nem agent, de ahhoz is ezt kéne használni??
        //Megoldás talán: Usable interface(UsableEquipment helyett) amit az agentek megvalósítanak, és a use-t egy Usable-n hívja meg
        if(commandList.length>3){
            throw new IllegalArgumentException("Too many arguments");
        }
        if(commandList.length<3){
            throw new IllegalArgumentException("Not enough arguments");
        }
        Virologist v = controller.getPlayerByName(commandList[2]);
        for (Agent agent: controller.getActivePlayer().getInventory().getCraftedAgents()){
            if(agent.toString().contains(commandList[1])){
                controller.use(agent, v);
                return;
            }
        }
    }

    private static void craft(){
        if(commandList.length>2){
            throw new IllegalArgumentException("Too many arguments");
        }
        if(commandList.length<2){
            throw new IllegalArgumentException("Not enough arguments");
        }
        for (GeneticCode geneticCode: controller.getActivePlayer().getInventory().getLearntCodes()){
            if(geneticCode.getAgent().toString().contains(commandList[1])){
                controller.craft(geneticCode);
                return;
            }
        }
    }

    private static void steal(){
        //TODO
    }

    private static void drop(){
        for(Equipment equipment : controller.getActivePlayer().getInventory().getEquipments()){
            if(equipment.toString().contains(commandList[1])){
                controller.drop(equipment);
                return;
            }
        }
    }

    private static void pass() {
        controller.pass();
    }

    private static void info(){//TODO
        int i=1;
        boolean setObject=false;
        Virologist virologist=null;
        ArrayList<Integer> numbers=new ArrayList<>();
        while(i<commandList.length){
            if(commandList[i].equals("--o")){
                setObject=true;
                virologist=controller.getPlayerByName(commandList[++i]);
                if(virologist!=null){
                    Tile tile=controller.getTileByName(commandList[i]);
                }
            }
            else if(commandList[i].equals("--f")){
                System.out.println("filename: " + commandList[++i]);
                //TODO
            }
            else if(commandList[i].equals("--n")){
                while(++i<commandList.length && !commandList[i].contains("--")){
                    numbers.add(Integer.parseInt(commandList[i]));
                }
            }
            i++;
        }

        if(!setObject){
            virologist = controller.getActivePlayer();
        }
        System.out.println("Készítés alatt.");
        System.out.println("számok:");
        for (Integer n: numbers) {{
            System.out.println(n);
        }

        }
        System.out.println(virologist.getName());
    }

    private static void setNextRandom(){
        if(commandList.length>2){
            throw new IllegalArgumentException("Too many arguments");
        }
        if(commandList.length<2){
            throw new IllegalArgumentException("Not enough arguments");
        }
        SRandom.add(Integer.parseInt(commandList[1]));
    }

}


