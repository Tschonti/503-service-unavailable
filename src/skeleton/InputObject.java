package skeleton;

/**
 * Helper class for the Initializer class. Represents an option in a question to the user.
 * Wraps the options index and content as a single object.
 */
public class InputObject {
    private final int index;
    private final String name;

    /**
     * Constructor
     * @param index Index of the option
     * @param name  Name (content) of the option
     */
    public InputObject(int index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     * Getter for the index
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for the name
     * @return name
     */
    public String getName() {
        return name;
    }
}
