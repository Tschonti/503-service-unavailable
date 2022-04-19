package main;

import agents.Agent;
import equipments.Equipment;
import equipments.UsableEquipment;
import tiles.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleView implements View {

    /**
     * Controller of the game.
     */
    static Controller controller;

    /**
     *
     */
    private final HashMap<String, Command> menu;

    /**
     *
     */
    private final HashMap<String, Command> actions;

    /**
     *
     */
    private static String[] commandList;

    /**
     *
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     *
     */
    private boolean quitMenu = false;

    /**
     *
     */
    private static final OutputGenerator.VirologistInfoItem[] virologistInfoItems = {
        OutputGenerator::generateName,
        OutputGenerator::generateActionsLeft,
        OutputGenerator::generateTile,
        OutputGenerator::generateNeighbours,
        OutputGenerator::generateVirologistsOnTile,
        OutputGenerator::generateStunnedVirologists,
        OutputGenerator::generateCollectable,
        OutputGenerator::generateResources,
        OutputGenerator::generateGeneticCodes,
        OutputGenerator::generateUsables,
        OutputGenerator::generateEquipments,
        OutputGenerator::generateEffects,
    };

    /**
     *
     */
    private static final OutputGenerator.TileInfoItem[] tileInfoItems = {
        OutputGenerator::generateName,
        OutputGenerator::generateID,
        OutputGenerator::generateType,
        OutputGenerator::generateNeighbours,
        OutputGenerator::generateCollectable,
    };

    /**
     * Constructor
     */
    public ConsoleView() {
        controller = new Controller(this);
        menu = new HashMap<>();
        menu.put("start", controller::gameLoop);
        menu.put("add", ConsoleView::add);
        menu.put("quit", () -> quitMenu = true);

        actions = new HashMap<>();
        actions.put("collect", ConsoleView::collect);
        actions.put("move", ConsoleView::move);
        actions.put("use", ConsoleView::use);
        actions.put("craft", ConsoleView::craft);
        actions.put("steal", ConsoleView::steal);
        actions.put("drop", ConsoleView::drop);
        actions.put("pass", ConsoleView::pass);
        actions.put("info", ConsoleView::info);
        actions.put("end", controller::endGame);
        actions.put("quit", controller::quit);
        if (Main.getDebugMode()) {
            actions.put("setnextrandom", ConsoleView::setNextRandom);
        }
    }

    /**
     *
     */
    public interface Command {
        /**
         * Starts the testcase.
         */
        void run();
    }

    /**
     * The menu loop of the game.
     */
    public void menu() {
        while (!quitMenu) {
            writeMenu();
            try {
                getNextLine();
                Command toRun = menu.get(commandList[0]);
                if (toRun != null) {
                    toRun.run();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Writes a nice menu on the console.
     */
    public void writeMenu() {
        System.out.println("---------MENU---------");
        System.out.println("start");
        System.out.println("add <player> (<tile>)");
        System.out.println("quit");
        System.out.println();
        if (!controller.getPlayers().isEmpty()) {
            System.out.println("Players in lobby:");
            for (Virologist player : controller.getPlayers()) {
                System.out.println("1. " + player.getName());
            }
            System.out.println();
        }
    }

    /**
     *
     */
    public void chooseAction() {
        while (true) {
            try {
                getNextLine();
                if (commandList.length > 0) {
                    Command toRun = actions.get(commandList[0]);
                    if (toRun != null) {
                        toRun.run();
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *
     * @param list
     * @return
     */
    public static int chooseOption(List<String> list) {
        System.out.println("Choose one:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
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

    /**
     * Writes out the winner of the game.
     * @param winner
     */
    public void gameOver(Virologist winner) {
        System.out.println("End of the game, winner: " + winner.getName());
    }

    /**
     * Add a new player to the game.
     */
    public static void add() {
        parameterCountCheck(2, 3);
        Virologist virologist = new Virologist(commandList[1]);
        if (commandList.length == 3) {
            controller.addPlayer(virologist, commandList[2]);
        } else {
            controller.addPlayer(virologist, "Hungary");
        }
    }

    /**
     * Collect action.
     */
    private static void collect() {
        controller.collect();
    }

    /**
     * Move action.
     */
    private static void move() {
        parameterCountCheck(2, 2);
        Tile tile = controller.getTileByName(commandList[1]);
        controller.move(tile);
    }

    /**
     * Use action.
     */
    private static void use() {
        parameterCountCheck(3, 3);
        Virologist to = controller.getPlayerByName(commandList[2]);
        Inventory fromInv = controller.getActivePlayer().getInventory();

        for (Agent agent : fromInv.getCraftedAgents()) {
            if (agent.toString().toLowerCase().contains(commandList[1])) {
                controller.use(agent, to);
                return;
            }
        }
        for (UsableEquipment ue : fromInv.getUsableEquipments()) {
            if (ue.toString().contains(commandList[1])) {
                controller.use(ue, to);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid agent or equipment!");
    }

    /**
     * Craft action.
     */
    private static void craft() {
        parameterCountCheck(2, 2);
        for (GeneticCode geneticCode: controller.getActivePlayer().getInventory().getLearntCodes()) {
            if (geneticCode.getAgent().toString().toLowerCase().contains(commandList[1])) {
                controller.craft(geneticCode);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid agent!");
    }

    /**
     * Steal action.
     */
    private static void steal() {
        parameterCountCheck(2, 2);
        Virologist to = controller.getPlayerByName(commandList[1]);
        ArrayList<Equipment> eqs = to.getInventory().getEquipments();
        Equipment toSteal = null;
        if (eqs.size() != 0) {
            toSteal =
                eqs.get(
                    chooseOption(eqs.stream().map(Object::toString).collect(Collectors.toList()))
                );
        }
        controller.steal(to, toSteal);
    }

    /**
     * Drop action.
     */
    private static void drop() {
        parameterCountCheck(2, 2);
        for(Equipment equipment : controller.getActivePlayer().getInventory().getEquipments()){
            if(equipment.toString().toLowerCase().contains(commandList[1])){
                controller.drop(equipment);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid equipment!");
    }

    /**
     * Pass action.
     */
    private static void pass() {
        controller.pass();
    }

    /**
     *
     */
    private static void info() {
        int i = 1;
        boolean setObject = false;
        Virologist virologist = null;
        Tile tile = null;
        ArrayList<Integer> numbers = new ArrayList<>();
        while (i < commandList.length) {
            if (commandList[i].equals("--o")) {
                setObject = true;
                try {
                    virologist = controller.getPlayerByName(commandList[++i]);
                } catch (IllegalArgumentException e) {
                    tile = controller.getTileByName(commandList[i]);
                }
            } else if (commandList[i].equals("--n")) {
                while (++i < commandList.length && !commandList[i].contains("--")) {
                    numbers.add(Integer.parseInt(commandList[i]));
                }
            }
            i++;
        }

        if (!setObject) {
            virologist = controller.getActivePlayer();
        }

        StringBuilder output = new StringBuilder();
        if (virologist != null) {
            Virologist finalVirologist = virologist;
            if (numbers.size() == 0) {
                for (int j = 1; j < 13; j++) {
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
                for (int j = 1; j < 6; j++) {
                    numbers.add(j);
                }
            }
            numbers.forEach(n -> {
                if (n < 1 || n > tileInfoItems.length) {
                    throw new IllegalArgumentException("Invalid parameter to info command!");
                }
                output.append(tileInfoItems[n - 1].generate(finalTile));
            });
        }
        System.out.print(output);
    }

    /**
     *
     */
    private static void setNextRandom() {
        parameterCountCheck(2, 2);
        SRandom.add(Integer.parseInt(commandList[1]));
    }

    /**
     *
     * @param min
     * @param max
     */
    private static void parameterCountCheck(int min, int max) {
        if (commandList.length > max) {
            throw new IllegalArgumentException("Too many arguments");
        }
        if (commandList.length < min) {
            throw new IllegalArgumentException("Not enough arguments");
        }
    }

    /**
     *
     */
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
