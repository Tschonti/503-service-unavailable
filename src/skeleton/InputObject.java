package skeleton;

public class InputObject {
    private final int index;
    private final String name;

    public InputObject(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
