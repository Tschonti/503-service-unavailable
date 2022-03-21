package main;

import skeleton.ConsoleColor;
import skeleton.Initializer;

public class Main {

    static public void main(String[] args) {

        System.out.println(ConsoleColor.BOLD.c + "Welcome to the Pandemic brave Virologist, let's start learning genetic codes to stop the\n" +
                "outbreak and cure your fellow humans. Be aware! There are other Virologists among you \n"+
                "that are not so friendly and attack you in humanity's last hours!\n"+
                "Save our world, mighty traveller!" + ConsoleColor.RESET.c);

        /*System.out.println("use cases:");
        ArrayList<String> useCases = new ArrayList<>();
        useCases.add("Move");
        useCases.add("Collect");
        for(int i = 0; i < useCases.size(); i++){
            System.out.println(i+1+". "+useCases.get(i)); //The ultimate not bullshit.  No Yes No YesNoNoNoNoNoNoNoNoNoNoNoNoNo
        }*/

        Initializer.test();
    }
}
