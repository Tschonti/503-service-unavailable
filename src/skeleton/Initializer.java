package skeleton;

import agents.*;
import equipments.Bag;
import equipments.Glove;
import main.GeneticCode;
import main.Inventory;
import main.Virologist;
import tiles.EmptyTile;
import tiles.Laboratory;
import tiles.Safehouse;
import tiles.Warehouse;

import java.io.IOException;
import java.util.*;


public class Initializer {

    static private boolean quit=false;
    static private int tabs = 0;
    private static final HashMap<Object, String> objects = new HashMap<>();
    static HashMap<String, TestCase> testcases = new HashMap<>();

    public static boolean questionWrite(String question) {
        System.out.println(ConsoleColor.BLUE.c + question+ ConsoleColor.BOLD.c + " (y/n)" + ConsoleColor.RESET.c);
        char reply = ' ';
        try {
            reply = (char)System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply == 'y';
    }

    public static InputObject questionListWrite(String question, ArrayList<String> options) {
        System.out.println(ConsoleColor.BLUE.c + question + ConsoleColor.RESET.c);
        int reply;
        for (int i = 0; i < options.size(); i++) {
            String c = (options.get(i) == "quit program") ? ConsoleColor.BLUE.c : ConsoleColor.RED.c;
            System.out.println(c + ConsoleColor.BOLD.c + (i+1) + ". " + options.get(i) + ConsoleColor.RESET.c);
        }
        Scanner s = new Scanner(System.in);
        InputObject result = null;
        boolean ready = false;
        while (!ready) {
            if (s.hasNextInt()) {
                reply = s.nextInt();
                try {
                    String choice = options.get(reply - 1);
                    result = new InputObject(reply, choice);
                    ready = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid choice");
                }
            } else {
                s.next();
            }

        }

        return result;
    }

    public static void functionWrite(OutputObject caller, String methodName, OutputObject[] params) {
        doTabs();
        System.out.print(
                "[" +
                ConsoleColor.RED.c + outputObjectToString(caller) + ConsoleColor.RESET.c +
                "] [" +
                ConsoleColor.CYAN.c + methodName + ConsoleColor.RESET.c +
                "("
        );
        int i=0;
        if (params != null) {
            for (OutputObject o : params) {
                System.out.print(ConsoleColor.GREEN.c + outputObjectToString(o) + ((params.length-1 == (i++)) ? "" : ", " ));
            }
        }
        System.out.print(ConsoleColor.RESET.c + ")" + ConsoleColor.RESET.c +"]\n");
        tabs++;
    }

    public static void returnWrite(OutputObject returned) {
        tabs--;
        doTabs();
        System.out.println(
            ConsoleColor.YELLOW.c + ConsoleColor.BOLD.c + "return "+ ConsoleColor.RESET.c +
                    "[" +
                    ConsoleColor.MAGENTA.c +
                    (returned == null ? "void" : returned.className) +
                    ConsoleColor.RESET.c +
                    "]"
        );

    }

    private static void doTabs() {
        for(int i=0; i<tabs; i++){
            System.out.print(("\t"));
        }
    }

    private static String outputObjectToString(OutputObject o) {
        if (o == null) return "void";
        String id = objects.get(o.ref);
        return o.className + ((id == null) ? "" : " " + id);
    }

    public static void test() {
        testcases.put("moveToEmptyTile", Initializer::moveToEmptyTile);
        testcases.put("moveToLaboratory", Initializer::moveToLaboratory);
        testcases.put("moveToSafeHouse", Initializer::moveToSafeHouse);
        testcases.put("moveToWareHouse", Initializer::moveToWareHouse);
        testcases.put("pickUpBag", Initializer::pickUpBag);
        testcases.put("pickUpCloak", Initializer::pickUpCloak);
        testcases.put("pickUpGlove", Initializer::pickUpGlove);
        testcases.put("pickUpResource", Initializer::pickUpResource);
        testcases.put("learnGeneticCode", Initializer::learnGeneticCode);
        testcases.put("craftStunVirus", Initializer::craftStun);
        testcases.put("craftAmnesiaVirus", Initializer::craftAmnesia);
        testcases.put("craftVitusDanceVirus", Initializer::craftVitusDance);
        testcases.put("craftVaccine", Initializer::craftVaccine);
        testcases.put("useStunVirus", Initializer::useStunVirus);
        testcases.put("useAmnesiaVirus", Initializer::useAmnesiaVirus);
        testcases.put("useVitusDanceVirus", Initializer::useVitusDanceVirus);
        testcases.put("useVaccine", Initializer::useVaccine);
        testcases.put("robVirologist", Initializer::robVirologist);
        testcases.put("forgetCodesViaAmnesiaVirus", Initializer::forgetCodes);
        testcases.put("enlargeTheBag", Initializer::enlargeTheBag);
        testcases.put("stunnedPlayerMissesTurn", Initializer::stunnedMissesTurn);
        testcases.put("playerVitusDances", Initializer::playerVitusDances);
        testcases.put("quit program",()->{quit=true; System.out.println("Bye");});

        ArrayList<String> tests = new ArrayList<>();
        testcases.forEach((name, object) -> tests.add(name));
        tests.sort(Initializer::sort);
        while (!quit) {
            InputObject input = questionListWrite("Which test case would you like to run?", tests);
            testcases.get(input.getName()).run();
        }
    }

    public static int sort(String a, String b)
    {
        String[] s1=testcases.get(a).toString().split("\\$");
        String[] s2=testcases.get(b).toString().split("\\$");
        int i1=Integer.parseInt(s1[s1.length - 1].split("/")[0]);
        int i2=Integer.parseInt(s2[s2.length - 1].split("/")[0]);
        return i1-i2;
    }

    public interface TestCase {
        void run();
    }

    public static void craftStun() {
        objects.clear();
        Virologist v=new Virologist();
        objects.put(v,"v");
        Agent sV=new StunVirus();
        objects.put(sV,"sV");
        GeneticCode gC=new GeneticCode();
        objects.put(gC,"gC");
        gC.setAgent(sV);
        Inventory i=v.getInventory();
        objects.put(i,"i");
        i.addGeneticCode(gC);
        v.craft(gC);
    }

    public static void craftAmnesia() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        Agent aV = new AmnesiaVirus();
        objects.put(aV, "aV");
        GeneticCode gC = new GeneticCode();
        objects.put(gC, "gC");
        gC.setAgent(aV);
        Inventory i = v.getInventory();
        objects.put(i, "i");
        i.addGeneticCode(gC);
        v.craft(gC);
    }

    public static void craftVitusDance() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        Agent vDV = new VitusDanceVirus();
        objects.put(vDV, "vDV");
        GeneticCode gC = new GeneticCode();
        objects.put(gC, "gC");
        gC.setAgent(vDV);
        Inventory i = v.getInventory();
        objects.put(i, "i");
        i.addGeneticCode(gC);
        v.craft(gC);
    }

    public static void craftVaccine() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        Agent vac = new Vaccine();
        objects.put(vac, "vac");
        GeneticCode gC = new GeneticCode();
        objects.put(gC, "gC");
        gC.setAgent(vac);
        Inventory i = v.getInventory();
        objects.put(i, "i");
        i.addGeneticCode(gC);
        v.craft(gC);
    }

    public static void moveToEmptyTile() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        EmptyTile tile = new EmptyTile(0, "from");
        v.setActiveTile(tile);
        EmptyTile tile2 = new EmptyTile(1, "to");
        v.moveTo(tile2);
    }

    public static void moveToLaboratory() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        EmptyTile tile = new EmptyTile(0, "from");
        v.setActiveTile(tile);
        Laboratory tile2 = new Laboratory(1, "to");
        v.moveTo(tile2);
    }

    public static void moveToSafeHouse() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        EmptyTile tile = new EmptyTile(0, "from");
        v.setActiveTile(tile);
        Safehouse tile2 = new Safehouse(1, "to");
        v.moveTo(tile2);
    }

    public static void moveToWareHouse() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        EmptyTile tile = new EmptyTile(0, "from");
        v.setActiveTile(tile);
        Warehouse tile2 = new Warehouse(1, "to");
        v.moveTo(tile2);
    }

    public static void pickUpBag() {

    }

    public static void pickUpCloak() {

    }

    public static void pickUpGlove() {

    }

    public static void pickUpResource() {

    }

    public static void learnGeneticCode() {

    }

    public static void useStunVirus() {

    }

    public static void useAmnesiaVirus() {

    }

    public static void useVitusDanceVirus() {

    }

    public static void useVaccine() {

    }

    public static void robVirologist() {
        objects.clear();
        Virologist v1 = new Virologist();
        objects.put(v1, "v1");

        Virologist v2 = new Virologist();
        objects.put(v2, "v2");

        StunVirus s = new StunVirus();
        objects.put(s, "s");

        v2.addEffect(s);

        Glove g = new Glove();
        v2.getInventory().addEquipment(g);

        v1.steal(v2);
    }

    public static void forgetCodes() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");

        GeneticCode gc = new GeneticCode();
        objects.put(gc, "gc");

        Inventory i = v.getInventory();
        objects.put(i, "i");

        i.addGeneticCode(gc);

        AmnesiaVirus av = new AmnesiaVirus();
        objects.put(av, "av");
        v.addEffect(av);

        v.myTurn();
    }

    public static void enlargeTheBag() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        Bag b = new Bag();
        objects.put(b, "b");
        v.getInventory().addEquipment(b);

        v.myTurn();
    }

    public static void stunnedMissesTurn() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");
        StunVirus sv = new StunVirus();
        objects.put(sv, "sv");
        v.addEffect(sv);

        v.myTurn();
    }

    public static void playerVitusDances() {
        objects.clear();
        Virologist v = new Virologist();
        objects.put(v, "v");

        EmptyTile et = new EmptyTile(1, "et");
        Laboratory l = new Laboratory(2, "l");
        objects.put(et, "et");
        objects.put(l, "l");
        et.addNeighbour(l);
        l.addNeighbour(et);
        et.addVirologist(v);
        v.setActiveTile(et);

        VitusDanceVirus vd = new VitusDanceVirus();
        objects.put(vd, "vd");
        v.addEffect(vd);

        v.myTurn();
    }
}
