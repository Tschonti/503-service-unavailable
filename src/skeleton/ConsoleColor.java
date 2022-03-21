package skeleton;

public enum ConsoleColor {
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    CYAN("\u001b[36m"),
    MAGENTA("\u001b[35m"),
    RESET("\u001b[0m"),
    BOLD("\u001b[1m");
    public final String c;
    ConsoleColor(String label) {
        this.c = label;
    }
}
