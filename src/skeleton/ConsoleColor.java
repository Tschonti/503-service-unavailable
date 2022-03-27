package skeleton;

/**
 * Enum for the console color codes for the IntelliJ IDEA.
 */
public enum ConsoleColor {
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    CYAN("\u001b[36m"),
    MAGENTA("\u001b[35m"),
    RESET("\u001b[0m"),
    BOLD("\u001b[1m");

    /**
     * This gets us the color.
     */
    public final String c;

    /**
     * Constructor
     * @param label The color we want.
     */
    ConsoleColor(String label) {
        String classPath = System.getProperty("java.class.path");
        if(classPath.contains("idea_rt.jar")) {
            this.c = label;
        } else {
            this.c = "";
        }
    }
}
