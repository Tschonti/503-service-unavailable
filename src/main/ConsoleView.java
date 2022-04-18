package main;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
import tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleView implements View {
    static Controller controller;
    private HashMap<String, Command> menu;
    private HashMap<String, Command> actions;
    private static String[] commandList;
    private static final Scanner scanner = new Scanner(System.in);
    private boolean quitMenu = false;
    private boolean quitGame = false;
    private static final OutputGenerator.VirologistInfoItem[] virologistInfoItems = {
            OutputGenerator::generateName, OutputGenerator::generateActionsLeft,
            OutputGenerator::generateTile, OutputGenerator::generateNeighbours ,
            OutputGenerator::generateVirologistsOnTile, OutputGenerator::generateStunnedVirologists,
            OutputGenerator::generateCollectable, OutputGenerator::generateResources,
            OutputGenerator::generateGeneticCodes, OutputGenerator::generateUsables,
            OutputGenerator::generateEquipments, OutputGenerator::generateEffects
    };
    private static final OutputGenerator.TileInfoItem[] tileInfoItems = {
            OutputGenerator::generateName, OutputGenerator::generateID,
            OutputGenerator::generateType, OutputGenerator::generateNeighbours,
            OutputGenerator::generateCollectable
    };


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
        actions.put("quit", controller::quit);  //TODO lehet át kéne nevezni?
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
                getNextLine();
                menu.get(commandList[0]).run();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void chooseAction() {
        while(true){
            try {
                getNextLine();
                actions.get(commandList[0]).run();
                break;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static int chooseOption(List<String> list) {
        System.out.println("Choose one:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + list.get(i));
        }
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice > 0 && choice <= list.size()) {
                    return choice - 1;
                } else {
                    System.out.println("Invalid choice!");
                }
            } else {
                scanner.next();
                System.out.println("Invalid choice!");
            }
        }
    }

    public void gameOver(Virologist winner) {
        System.out.println("End of the game, winner: " + winner.getName());
    }

    public static void add() {
        parameterCountCheck(2, 3);
        Virologist virologist = new Virologist(commandList[1]);
        if(commandList.length==3){
            controller.addPlayer(virologist, commandList[2]);
        }
        else{
            controller.addPlayer(virologist, "Hungary");//TODO ez igy jo hogy fix mezore kerul?
        }
    }

    private static void collect() {
        controller.collect();
    }

    private static void move() {
        parameterCountCheck(2, 2);
        Tile tile=controller.getTileByName(commandList[1]);
        controller.move(tile);
    }

    private static void use() {
        parameterCountCheck(3, 3);
        Virologist to = controller.getPlayerByName(commandList[2]);
        Inventory fromInv = controller.getActivePlayer().getInventory();

        for (Agent agent: fromInv.getCraftedAgents()){
            if(agent.toString().toLowerCase().contains(commandList[1])){
                controller.use(agent, to);
                return;
            }
        }
        for (UsableEquipment ue: fromInv.getUsableEquipments()) {
            if(ue.toString().contains(commandList[1])){
                controller.use(ue, to);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid agent or equipment!");
    }

    private static void craft(){
        parameterCountCheck(2, 2);
        for (GeneticCode geneticCode: controller.getActivePlayer().getInventory().getLearntCodes()){
            if(geneticCode.getAgent().toString().contains(commandList[1])){
                controller.craft(geneticCode);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid agent!");
    }

    private static void steal(){
        parameterCountCheck(2, 2);
        Virologist to = controller.getPlayerByName(commandList[1]);
        ArrayList<Equipment> eqs = to.getInventory().getEquipments();
        controller.steal(to, eqs.get(chooseOption(eqs.stream().map(Object::toString).collect(Collectors.toList()))));
    }

    private static void drop(){
        parameterCountCheck(2, 2);
        for(Equipment equipment : controller.getActivePlayer().getInventory().getEquipments()){
            if(equipment.toString().contains(commandList[1])){
                controller.drop(equipment);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid equipment!");
    }

    private static void pass() {
        controller.pass();
    }

    private static void info(){
        int i=1;
        boolean setObject=false;
        Virologist virologist=null;
        Tile tile = null;
        ArrayList<Integer> numbers=new ArrayList<>();
        while(i<commandList.length){
            if(commandList[i].equals("--o")){
                setObject=true;
                try {
                    virologist=controller.getPlayerByName(commandList[++i]);
                } catch (IllegalArgumentException e) {
                    tile=controller.getTileByName(commandList[i]);
                }
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

        StringBuilder output = new StringBuilder();
        if (virologist != null) {
            Virologist finalVirologist = virologist;
            if (numbers.size() == 0) {
                for (int j = 1; j < 13; j++ ) {
                    numbers.add(j);
                }
            }
            numbers.forEach(n -> {
                if (n < 1 || n > virologistInfoItems.length) {
                    throw new IllegalArgumentException("Invalid parameter to info command!");
                }
                output.append(virologistInfoItems[n - 1].generate(finalVirologist));
            });
        } else {
            Tile finalTile = tile;
            if (numbers.size() == 0) {
                for (int j = 1; j < 6; j++ ) {
                    numbers.add(j);
                }
            }
            numbers.forEach(n -> {
                if (n < 0 || n > tileInfoItems.length - 1) {
                    throw new IllegalArgumentException("Invalid parameter to info command!");
                }
                output.append(tileInfoItems[n].generate(finalTile));
            });
        }
        System.out.print(output);
    }

    private static void setNextRandom(){
        parameterCountCheck(2, 2);
        SRandom.add(Integer.parseInt(commandList[1]));
    }

    private static void parameterCountCheck(int min, int max) {
        if (commandList.length > max){
            throw new IllegalArgumentException("Too many arguments");
        }
        if (commandList.length < min){
            throw new IllegalArgumentException("Not enough arguments");
        }
    }

    private void getNextLine() {
        if (scanner.hasNextLine()) {
            commandList = scanner.nextLine().split(" ");
            for (int i = 0; i < commandList.length; i++) {
                commandList[i] = commandList[i].toLowerCase();
            }
        } else {
            System.exit(0);
        }
    }

}


