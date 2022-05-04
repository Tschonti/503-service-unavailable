package main;

/**
 * The Main class.
 */
public class Main {

    /**
     * True, if debug mode is on.
     */
    private static boolean debugMode = false;

    /**
     * The main function.
     * @param args arguments
     */
    public static void main(String[] args) {
        /*if (args.length > 0 && args[0].equals("debug")) {
            debugMode = true;
        }
        if (!debugMode) {
            System.out.println(
                "Welcome to the Pandemic brave Virologist, let's start learning genetic codes to stop the\n" +
                "outbreak and cure your fellow humans. Be aware! There are other Virologists among you \n" +
                "that are not so friendly and attack you in humanity's last hours!\n" +
                "Save our world, mighty traveller!"
            );
        }*/

        //ConsoleView consoleView = new ConsoleView();
        //consoleView.menu();

        new GraphicsView();

    }

    /**
     * Getter for debugMode.
     * @return debugMode
     */
    public static boolean getDebugMode() {
        return debugMode;
    }
}
