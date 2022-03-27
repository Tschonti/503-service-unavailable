package skeleton;

import agents.*;
import equipments.Bag;
import equipments.Glove;
import equipments.ProtectiveCloak;
import main.*;
import tiles.EmptyTile;
import tiles.Laboratory;
import tiles.Safehouse;
import tiles.Warehouse;

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
     * 'y' means yes and 'n' means no.
     * @param question The full text of the question.
     * @return Returns the answer to the question, true means yes, false means no.
     */
    public static boolean questionYesOrNo(String question) {
        System.out.println(ConsoleColor.BLUE.c + question+ ConsoleColor.BOLD.c + " (y/n)" + ConsoleColor.RESET.c);
        char reply = ' '; //TODO samu??????
        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        while(!finished){
            reply = sc.next().charAt(0);
            finished = reply == 'y' || reply =='n';
            if(!finished){
                System.out.println("Invalid choice");
            }
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
            String c = (options.get(i).equals("quit program")) ? ConsoleColor.RED.c : ConsoleColor.BLUE.c;
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
                    result = new InputObject(reply - 1, choice);
                    ready = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid choice");
                }
            } else {
                System.out.println("Invalid choice");
                s.next();
            }

        }

        return result;
    }

    /**
     * Writes an objects name, methods name, and parameters to the console.
     * @param caller The called objects reference.
     * @param methodName The called functions name.
     * @param params The parameters of the function.
     */
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
        testcases.put("quit program",()->{quit=true; System.out.println("Bye");});

        ArrayList<String> tests = new ArrayList<>();
        testcases.forEach((name, object) -> tests.add(name));
        tests.sort(Initializer::compareOrder);
        while (!quit) {
            InputObject input = questionListWrite("Which test case would you like to run?", tests);
            testcases.get(input.getName()).run();
        }
    }

    /**
     * Tells between two test case's name which got put earlier in the testcases.
     * @param a A test case's name
     * @param b B test case's name
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
        Virologist v=new Virologist("Virologist1");
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
        Virologist v = new Virologist("Virologist1");
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
        Virologist v = new Virologist("Virologist1");
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
        Virologist v = new Virologist("Virologist1");
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
        Virologist v = new Virologist("Vir");
        objects.put(v, "v");
        EmptyTile tile2 = new EmptyTile(1, "to");
        objects.put(tile2, "e");
        EmptyTile activeTile = new EmptyTile(0, "from");
        objects.put(activeTile, "activeTile");
        v.setActiveTile(activeTile);
        tile2.addNeighbour(activeTile);
        activeTile.addNeighbour(tile2);
        activeTile.addVirologist(v);

        v.moveTo(tile2);
    }

    public static void moveToLaboratory() {
        objects.clear();
        Virologist v = new Virologist("Vir");
        objects.put(v, "v");
        Laboratory tile2 = new Laboratory(1, "to");
        objects.put(tile2, "l");
        EmptyTile activeTile = new EmptyTile(0, "from");
        objects.put(activeTile, "activeTile");
        v.setActiveTile(activeTile);
        tile2.addNeighbour(activeTile);
        activeTile.addNeighbour(tile2);
        activeTile.addVirologist(v);

        v.moveTo(tile2);
    }

    public static void moveToSafeHouse() {
        objects.clear();
        Virologist v = new Virologist("Vir");
        objects.put(v, "v");
        Safehouse tile2 = new Safehouse(1, "to");
        objects.put(tile2, "s");
        EmptyTile activeTile = new EmptyTile(0, "from");
        objects.put(activeTile, "activeTile");
        v.setActiveTile(activeTile);
        tile2.addNeighbour(activeTile);
        activeTile.addNeighbour(tile2);
        activeTile.addVirologist(v);

        v.moveTo(tile2);
    }

    public static void moveToWareHouse() {
        objects.clear();
        Virologist v = new Virologist("Vir");
        objects.put(v, "v");
        Warehouse tile2 = new Warehouse(1, "to");
        objects.put(tile2, "w");
        EmptyTile activeTile = new EmptyTile(0, "from");
        objects.put(activeTile, "activeTile");
        v.setActiveTile(activeTile);
        tile2.addNeighbour(activeTile);
        activeTile.addNeighbour(tile2);
        activeTile.addVirologist(v);

        v.moveTo(tile2);
    }

    public static void pickUpBag() {
        objects.clear();
        Safehouse s = new Safehouse(1, "SafeHouse1", Bag.class);
        objects.put(s, "s");
        Virologist v = new Virologist("Virologist1");
        objects.put(v, "v");
        v.setActiveTile(s);
        s.addVirologist(v);

        s.collectItem(v.getInventory());
    }

    public static void pickUpCloak() {
        objects.clear();
        Safehouse s = new Safehouse(1, "SafeHouse1", ProtectiveCloak.class);
        objects.put(s, "s");
        Virologist v = new Virologist("Virologist1");
        objects.put(v, "v");
        v.setActiveTile(s);
        s.addVirologist(v);

        s.collectItem(v.getInventory());
    }

    public static void pickUpGlove() {
        objects.clear();
        Safehouse s = new Safehouse(1, "SafeHouse1", Glove.class);
        objects.put(s, "s");
        Virologist v = new Virologist("Virologist1");
        objects.put(v, "v");
        v.setActiveTile(s);
        s.addVirologist(v);

        s.collectItem(v.getInventory());
    }

    public static void pickUpResource() {
        objects.clear();
        Warehouse s = new Warehouse(1, "WareHouse1");
        objects.put(s, "s");
        Virologist v = new Virologist("Virologist1");
        objects.put(v, "v");
        v.setActiveTile(s);
        s.addVirologist(v);

        s.collectItem(v.getInventory());
    }

    public static void learnGeneticCode() {
        objects.clear();
        Controller c = new Controller();
        objects.put(c, "c");
        Virologist v = new Virologist("Vir");
        objects.put(v, "v");
        Virologist.setController(c);
        c.addPlayer(v);
        Laboratory l = new Laboratory(1, "lab");
        objects.put(l, "l");
        l.addVirologist(v);
        v.setActiveTile(l); //TODO for Samu, ez nincs rajt a kommunikációson

        v.pickUp();
    }

    //TODO for Zsombi, a kommunikációs diagramm szerinti sorrendben kell történnie a dolgoknak
    //TODO for Zsomni, a kérdések legyenek angolul
    public static void useStunVirus() {
        objects.clear();
        Virologist v1 = new Virologist("Vir1");
        AmnesiaVirus sv = new AmnesiaVirus();
        Virologist v2 = new Virologist("Vir2");

        objects.put(v1, "v1");
        objects.put(v2, "v2");
        objects.put(sv, "sv");
        objects.put(v1.getInventory(), "inv");
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Vaccine effect?")) {
            Vaccine v = new Vaccine();
            objects.put(v, "v");
            v2.addEffect(v);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Glove?")) {
            Glove g1 = new Glove();
            objects.put(g1, "g1");
            v2.addEffect(g1);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a ProtectiveCloak?")) {
            ProtectiveCloak p1 = new ProtectiveCloak();
            objects.put(p1, "p1");
            v2.addEffect(p1);
        }
        sv.use(v1, v2);
    }

    public static void useAmnesiaVirus() {
        objects.clear();
        Virologist v1 = new Virologist("Vir1");
        AmnesiaVirus av = new AmnesiaVirus();
        Virologist v2 = new Virologist("Vir2");

        objects.put(v1, "v1");
        objects.put(v2, "v2");
        objects.put(av, "av");
        objects.put(v1.getInventory(), "inv");
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Vaccine effect?")) {
            Glove g1 = new Glove();
            objects.put(g1, "g1");
            v2.addEffect(g1);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Glove?")) {
            Vaccine v = new Vaccine();
            objects.put(v, "v");
            v2.addEffect(v);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a ProtectiveCloak?")) {
            ProtectiveCloak p1 = new ProtectiveCloak();
            objects.put(p1, "p1");
            v2.addEffect(p1);
        }
        av.use(v1, v2);
    }

    public static void useVitusDanceVirus() {
        objects.clear();
        Virologist v1 = new Virologist("Vir1");
        AmnesiaVirus vdv = new AmnesiaVirus();
        Virologist v2 = new Virologist("Vir2");

        objects.put(v1, "v1");
        objects.put(v2, "v2");
        objects.put(vdv, "vdv");
        objects.put(v1.getInventory(), "inv");
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Vaccine effect?")) {
            Vaccine v = new Vaccine();
            objects.put(v, "v");
            v2.addEffect(v);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Glove?")) {
            Glove g1 = new Glove();
            objects.put(g1, "g1");
            v2.addEffect(g1);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a ProtectiveCloak?")) {
            ProtectiveCloak p1 = new ProtectiveCloak();
            objects.put(p1, "p1");
            v2.addEffect(p1);
        }
        vdv.use(v1, v2);
    }

    public static void useVaccine() {
        objects.clear();
        Virologist v1 = new Virologist("Vir1");
        AmnesiaVirus v = new AmnesiaVirus();
        Virologist v2 = new Virologist("Vir2");

        objects.put(v1, "v1");
        objects.put(v2, "v2");
        objects.put(v, "v");
        objects.put(v1.getInventory(), "inv");
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Vaccine effect?")) {
            Vaccine vac = new Vaccine();
            objects.put(vac, "vac");
            v2.addEffect(vac);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a Glove?")) {
            Glove g1 = new Glove();
            objects.put(g1, "g1");
            v2.addEffect(g1);
        }
        if(Initializer.questionYesOrNo("Should the Virologist who gets thrown at have a ProtectiveCloak?")) {
            ProtectiveCloak p1 = new ProtectiveCloak();
            objects.put(p1, "p1");
            v2.addEffect(p1);
        }
        v.use(v1, v2);
    }

    public static void robVirologist() {
        objects.clear();
        Virologist v1 = new Virologist("Virologist1");
        objects.put(v1, "v1");

        Virologist v2 = new Virologist("Virologist2");
        objects.put(v2, "v2");

        StunVirus s = new StunVirus();
        objects.put(s, "s");

        v2.addEffect(s);

        Glove g = new Glove();
        Inventory v2Inv = v2.getInventory();
        objects.put(v2Inv, "v2Inv");
        v2Inv.addEquipment(g);

        v1.steal(v2);
    }

    public static void forgetCodes() {
        objects.clear();
        Virologist v = new Virologist("Virologist1");
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
        Virologist v = new Virologist("Virologist1");
        objects.put(v, "v");
        Bag b = new Bag();
        objects.put(b, "b");
        v.getInventory().addEquipment(b);

        v.myTurn();
    }

    public static void stunnedMissesTurn() {
        objects.clear();
        Virologist v = new Virologist("Virologist1");
        objects.put(v, "v");
        StunVirus sv = new StunVirus();
        objects.put(sv, "sv");
        v.addEffect(sv);

        v.myTurn();
    }

    public static void playerVitusDances() {
        objects.clear();
        Virologist v = new Virologist("Virologist1");
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
