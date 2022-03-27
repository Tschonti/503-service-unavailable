package main;

import skeleton.ConsoleColor;
import skeleton.Initializer;

public class Main {

    static public void main(String[] args) {
        System.out.println(ConsoleColor.BOLD.c + "Welcome to the Pandemic brave Virologist, let's start learning genetic codes to stop the\n" +
                "outbreak and cure your fellow humans. Be aware! There are other Virologists among you \n"+
                "that are not so friendly and attack you in humanity's last hours!\n"+
                "Save our world, mighty traveller!" + ConsoleColor.RESET.c);
        Initializer.test();
    }
}
