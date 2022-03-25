package skeleton;

import agents.*;
import equipments.Bag;
import equipments.Glove;
import main.GeneticCode;
import main.Inventory;
import main.Virologist;
import tiles.EmptyTile;
import tiles.Laboratory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Initializer {

    static private int tabs = 0;
    private static final HashMap<Object, String> objects = new HashMap<>();

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
            System.out.println(ConsoleColor.BLUE.c + ConsoleColor.BOLD.c + (i+1) + ". " + options.get(i) + ConsoleColor.RESET.c);
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
                ConsoleColor.RED.c + outputObjectToString(caller) +
                ConsoleColor.RESET.c +
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
        Virologist v1 = new Virologist();
        objects.put(v1, "v1");
        AmnesiaVirus av = new AmnesiaVirus();
        objects.put(av, "av");
        v1.addEffect(av);
        //questionWrite("Szeretnéd, hogy legyen a kedves kis virológusnak fasztyűje??");
        Glove g = new Glove();
        objects.put(g, "glove");
        g.allowStealing();

        HashMap<String, TestCase> testcases = new HashMap<>();

        //testcases.put("moveToEmptyTile");
        //testcases.put("moveToLaboratory");
        //testcases.put("moveToSafeHouse");
        //testcases.put("moveToWareHouse");
        //testcases.put("pickUpBag");
        //testcases.put("pickUpCloak");
        //testcases.put("pickUpGlove");
        //testcases.put("pickUpResource");
        //testcases.put("learnGeneticCode");
        testcases.put("craftStunVirus", new CraftStun());
        testcases.put("craftAmnesiaVirus", new CraftAmneisa());
        testcases.put("craftVitusDanceVirus", new CraftVitusDance());
        testcases.put("craftVaccine", new CraftVaccine());
        //testcases.put("useStunVirus");
        //testcases.put("useAmnesiaVirus");
        //testcases.put("useVitusDanceVirus");
        //testcases.put("useVaccine");
        testcases.put("robVirologist", new RobVirologist());
        testcases.put("forgetCodesViaAmnesiaVirus", new ForgetCodes());
        testcases.put("enlargeTheBag", new EnlargeTheBag());
        testcases.put("stunnedPlayerMissesTurn", new StunnedMissesTurn());
        testcases.put("playerVitusDances", new PlayerVitusDances());

        ArrayList<String> tests = new ArrayList<>();
        testcases.forEach((name, object) -> tests.add(name));
        InputObject input =  questionListWrite("Which test case would you like to run?", tests);
        testcases.get(input.getName()).startTestCase();

    }

    public interface TestCase {
        void startTestCase();
    }

    private static class CraftStun implements TestCase {
        public void startTestCase() {
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

    }

    private static class CraftAmneisa implements TestCase {
        public void startTestCase() {
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
    }

    private static class CraftVitusDance implements TestCase {
        public void startTestCase() {
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
    }

    private static class CraftVaccine implements TestCase {
        public void startTestCase() {
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
    }

    private static class RobVirologist implements TestCase {
        public void startTestCase() {
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
    }

    private static class ForgetCodes implements TestCase {
        public void startTestCase() {
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
    }

    private static class EnlargeTheBag implements TestCase {
        public void startTestCase() {
            objects.clear();
            Virologist v = new Virologist();
            objects.put(v, "v");
            Bag b = new Bag();
            objects.put(b, "b");
            v.getInventory().addEquipment(b);

            v.myTurn();
        }
    }

    private static class StunnedMissesTurn implements TestCase {
        public void startTestCase() {
            objects.clear();
            Virologist v = new Virologist();
            objects.put(v, "v");
            StunVirus sv = new StunVirus();
            objects.put(sv, "sv");
            v.addEffect(sv);

            v.myTurn();

        }
    }

    private static class PlayerVitusDances implements TestCase {
        public void startTestCase() {
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
}
