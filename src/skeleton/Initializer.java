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
import java.util.*;

/**
 * Handles the test cases of the project, initializes them, and then starts them.
 */
public class Initializer {
    /**
     * True, if the user wants to quit the testing.
     */
    static private boolean quit=false;
    /**
     * Stores the current indentation of the testcase.
     */
    static private int tabs = 0;
    /**
     * Stores the objects in the current testcase with their identifier names.
     */
    private static final HashMap<Object, String> objects = new HashMap<>();
    /**
     * Stores the testcase names with their functions.
     */
    static HashMap<String, TestCase> testcases = new HashMap<>();

    /**
     * Asks a yes/no question from the user, and waits for their answer. The answer needs to be written in the Console,
     * yes is 'y' and no is 'n'.
     * @param question The full text of the question.
     * @return Returns the answer to the question, true means yes, false means no.
     */
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

    /**
     * Asks a multiple choice question from the user, and waits for their answer.
     * Each option has an associated number from 1 to the number of the options. The user has to write this number in the console.
     * @param question The full text of the question.
     * @param options The possible answers to the question.
     * @return The number associated to the chosen answer.
     */
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

    /**
     * Writes an objects name, methods name, and parameters to the console.
     * @param called The called objects reference.
     * @param methodName The called functions name.
     * @param params The parameters of the function.
     */
    public static void functionWrite(OutputObject called, String methodName, OutputObject[] params) {
        doTabs();
        System.out.print(
                "[" +
                ConsoleColor.RED.c + outputObjectToString(called) +
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

    /**
     * Writes a functions return type and the value to the Console.
     * @param returned The returned functions object.
     */
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

    /**
     * Writes tabulators to the terminal. The number of tabulators is stored in the tabs variable.
     */
    private static void doTabs() {
        for(int i=0; i<tabs; i++){
            System.out.print(("\t"));
        }
    }

    /**
     * TODO..............................................................................................................
     * @param o valami
     * @return valami
     */
    private static String outputObjectToString(OutputObject o) {
        if (o == null) return "void";
        String id = objects.get(o.ref);
        return o.className + ((id == null) ? "" : " " + id);
    }

    /**
     * Creates every testcase and lists them on the console. The user chooses one, and then runs the chosen testcase.
     * This loops until the user quit.
     */
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
        testcases.put("quit program",()->quit=true);

        ArrayList<String> tests = new ArrayList<>();
        testcases.forEach((name, object) -> tests.add(name));
        tests.sort(Initializer::compareOrder);
        while (!quit) {
            InputObject input = questionListWrite("Which test case would you like to run?", tests);
            testcases.get(input.getName()).run();
        }
    }

    /**
     * Tells between two testcasename which got put earlier in the testcases.
     * @param a A testcase name
     * @param b B testcase name
     * @return positive, if 'a' parameter got put earlier, negative, if 'b' parameter.
     */
    public static int compareOrder(String a, String b)
    {
        String[] s1=testcases.get(a).toString().split("\\$");
        String[] s2=testcases.get(b).toString().split("\\$");
        int i1=Integer.parseInt(s1[s1.length - 1].split("/")[0]);
        int i2=Integer.parseInt(s2[s2.length - 1].split("/")[0]);
        return i1-i2;
    }

    /**
     * An interface for all the testcases.
     */
    public interface TestCase {
        /**
         * Starts the testcase.
         */
        void run();
    }

    /**
     * Testcase for the creation of a StunVirus.
     */
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

    /**
     * Testcase for the creation of an AmnesiaVirus.
     */
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

    /**
     * Testcase for the creation of an VitusDanceVirus.
     */
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

    /**
     * Testcase for the creation of a VaccineVirus.
     */
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

    }

    public static void moveToLaboratory() {

    }

    public static void moveToSafeHouse() {

    }

    public static void moveToWareHouse() {

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
