package skeleton;

/**
 * A helper class for the Initializer class that stores an objects reference and its classname.
 */
public class OutputObject {
    Object ref;
    String className;

    /**
     * Constructor for non-primitive objects
     * Generates the classname automatically
     * @param o the reference to the object
     */
    public OutputObject(Object o) {
        ref = o;
        if (o != null) {
            className = objectToName(o);
        }
    }

    /**
     * Constructor for booleans.
     * Reference is not stored, because the Initializer object will never know this variable.
     * @param b boolean variable
     */
    public OutputObject(boolean b) {
        className = "boolean";
    }

    /**
     * Constructor for strings.
     * Reference is not stored, because the Initializer object will never know this variable.
     * @param s String variable
     */
    public OutputObject(String s) {
        className = "string";
    }

    /**
     * Constructor when the method (whose caller should be displayed) is static
     * @param s Classname to be displayed in the terminal
     * @param isStatic  Whether the method is static. If it isn't, this constructor shouldn't be used.
     */
    public OutputObject(String s, boolean isStatic) {
        if (isStatic) {
            className = s;
        }
    }

    /**
     * Constructor for integers
     * Reference is not stored, because the Initializer object will never know this variable.
     * @param i integer variable
     */
    public OutputObject(int i) {
        className = "int";
    }

    /**
     * Constructor for doubles
     * Reference is not stored, because the Initializer object will never know this variable.
     * @param d double variable
     */
    public OutputObject(double d) {
        className = "double";
    }

    /**
     * Returns the classname of the objects default toString implementation.
     * @param o an object
     * @return Classname of the object
     */
    public static String objectToName(Object o) {
        String[] regex =  o.getClass().toString().split("\\.");
        return regex[regex.length - 1];
    }

    /**
     * Generates an OutputObjects array to which each parameter is passed as an OutputObject.
     * We can display the parameters of a method easily with this method.
     * @param params Any number of objects, all the parameters of the method we want to display
     * @return OutputObject array with all the params as OutputObject
     */
    public static OutputObject[] generateParamsArray(Object... params) {
        OutputObject[] res = new OutputObject[params.length];
        for (int i = 0; i < params.length; i++) {
            res[i] = new OutputObject(params[i]);
        }
        return res;
    }
}
