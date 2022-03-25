package skeleton;

import agents.AmnesiaVirus;
import agents.StunVirus;
import equipments.Bag;
import equipments.Glove;
import main.Virologist;
import tiles.Laboratory;

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

    public static int questionListWrite(String question, ArrayList<String> options) {
        System.out.println(ConsoleColor.BLUE.c + question+ ConsoleColor.BOLD.c + " (y/n)" + ConsoleColor.RESET.c);
        int reply = 0;
        for (int i = 0; i < options.size(); i++) {
            System.out.println(ConsoleColor.BLUE.c + ConsoleColor.BOLD.c + i+1 + ". " + options.get(i) + ConsoleColor.RESET.c);
        }
        try {
            reply = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
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
        questionWrite("Szeretnéd, hogy legyen a kedves kis virológusnak fasztyűje??");

        Glove g = new Glove();
        objects.put(g, "glove");
        g.allowStealing();

        samuTest();
    }

    public static void samuTest() {
        objects.clear();
        Virologist v1 = new Virologist();
        objects.put(v1, "v1");
        Laboratory l = new Laboratory(1, "hun");
        objects.put(l, "lab");
        l.addVirologist(v1);
        StunVirus sv = new StunVirus();
        objects.put(sv, "sv");
        v1.addEffect(sv);
        l.getPlayersToStealFrom();

        l.collectItem(v1.getInventory());

    }
}
