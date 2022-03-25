package skeleton;

import agents.*;
import equipments.Glove;
import main.GeneticCode;
import main.Inventory;
import main.Virologist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
    public static void functionWrite(OutputObject caller, String methodName, OutputObject[] params) {
        doTabs();
        System.out.print(
                "[" +
                ConsoleColor.RED.c + caller.className +
                (objects.get(caller.ref) == null ? "" : " " + objects.get(caller.ref)) +
                ConsoleColor.RESET.c +
                "] [" +
                ConsoleColor.CYAN.c + methodName + ConsoleColor.RESET.c +
                "("
        );
        int i=0;
        if (params != null) {
            for (OutputObject o : params) {
                System.out.print(ConsoleColor.GREEN.c + o.className + " " + objects.get(o.ref) + ((params.length-1 == (i++)) ? "" : ", " ));
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
                    (returned == null ? "void" : (returned.className +
                    (returned.ref == null ? " " + returned.value : (objects.get(returned.ref) == null ? "" : " " + objects.get(returned.ref))))) +
                    ConsoleColor.RESET.c +
                    "]"
        );

    }


    //public static void returnStringWrite(String returned) {
    //    tabs--;
    //    doTabs();
    //    System.out.println(
    //            ConsoleColor.YELLOW.c + ConsoleColor.BOLD.c + "return "+ ConsoleColor.RESET.c +
    //                    "[" +
    //                    ConsoleColor.MAGENTA.c + returned + ConsoleColor.RESET.c +
    //                    "]"
    //    );
    //}
    private static void doTabs() {
        for(int i=0; i<tabs; i++){
            System.out.print(("\t"));
        }
    }

    public static void test() {
        Virologist v1 = new Virologist();
        objects.put(v1, "v1");
        AmnesiaVirus av = new AmnesiaVirus();
        objects.put(av, "av");
        v1.addEffect(av);
        questionWrite("Szeretnéd, hogy legyen a kedves kis virológusnak fasztyűje??");
        Glove g = new Glove();
        objects.put(g, "glove");
        g.allowStealing();

        ArrayList<String> tests=new ArrayList<>();
        tests.add("moveToEmptyTile");
        tests.add("moveToLaboratory");
        tests.add("moveToSafeHouse");
        tests.add("moveToWareHouse");
        tests.add("pickUpBag");
        tests.add("pickUpCloak");
        tests.add("pickUpGlove");
        tests.add("pickUpResource");
        tests.add("learnGeneticCode");
        tests.add("craftStunVirus");
        tests.add("craftAmnesiaVirus");
        tests.add("craftVitusDanceVirus");
        tests.add("craftVaccine");
        tests.add("useStunVirus");
        tests.add("useAmnesiaVirus");
        tests.add("useVitusDanceVirus");
        tests.add("useVaccine");
        tests.add("robVirologist");
        tests.add("forgetCodesViaAmnesiaVirus");
        tests.add("enlargeTheBag");
        tests.add("stunnedPlayerMissesTurn");
        tests.add("playerVitusDances");


        for(int i=0; i < tests.size(); i++){
            System.out.println(i+1+":\t"+tests.get(i));
        }
    }

    public static void craftStun(){
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

    public static void craftAmnesia(){
        objects.clear();
        Virologist v=new Virologist();
        objects.put(v,"v");
        Agent aV=new AmnesiaVirus();
        objects.put(aV,"aV");
        GeneticCode gC=new GeneticCode();
        objects.put(gC,"gC");
        gC.setAgent(aV);
        Inventory i=v.getInventory();
        objects.put(i,"i");
        i.addGeneticCode(gC);
        v.craft(gC);
    }

    public static void craftVitusDance(){
        objects.clear();
        Virologist v=new Virologist();
        objects.put(v,"v");
        Agent vDV=new VitusDanceVirus();
        objects.put(vDV,"vDV");
        GeneticCode gC=new GeneticCode();
        objects.put(gC,"gC");
        gC.setAgent(vDV);
        Inventory i=v.getInventory();
        objects.put(i,"i");
        i.addGeneticCode(gC);
        v.craft(gC);
    }

    public static void craftVaccine(){
        objects.clear();
        Virologist v=new Virologist();
        objects.put(v,"v");
        Agent vac=new Vaccine();
        objects.put(vac,"vac");
        GeneticCode gC=new GeneticCode();
        objects.put(gC,"gC");
        gC.setAgent(vac);
        Inventory i=v.getInventory();
        objects.put(i,"i");
        i.addGeneticCode(gC);
        v.craft(gC);
    }



}
