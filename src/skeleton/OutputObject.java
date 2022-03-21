package skeleton;

public class OutputObject {
    Object ref;
    String className;
    String value;

    public OutputObject(Object o) {
        ref = o;
        if (o != null) {
            className = objectToName(o);
        }
    }

    public OutputObject(boolean b) {
        value = Boolean.toString(b);
        className = "boolean";
    }

    public OutputObject(String s) {
        value = s;
        className = "string";
    }

    public OutputObject(int i) {
        value = Integer.toString(i);
        className = "int";
    }

    public OutputObject(double d) {
        value = Double.toString(d);
        className = "double";
    }

    public static String objectToName(Object o) {
        return o.toString().split("@")[0].split("\\.")[1];
    }

    public static OutputObject[] generateParamsArray(Object... params) {
        OutputObject[] res = new OutputObject[params.length];
        for (int i = 0; i < params.length; i++) {
            res[i] = new OutputObject(params[i]);
        }
        return res;
    }
}
